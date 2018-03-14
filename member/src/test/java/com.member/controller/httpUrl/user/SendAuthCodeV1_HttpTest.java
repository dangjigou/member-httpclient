package com.member.controller.httpUrl.user;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpClient;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import com.member.test.utils.redis.RedisUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.member.test.utils.sign.Signature.getByRequest;

/**
 * Created by jackson on 16/6/2.
 */
public class SendAuthCodeV1_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(SendAuthCodeV1_HttpTest.class);
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String LAST_SEND_TIME_KEY = "last_send_time";
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    static HttpClient httpClient = new HttpClient();
    Map<String,String> codeParams = new HashMap<String, String>();
    RedisUtils redisUtils = new RedisUtils();

    /**
     * 脚本说明：
     * case01: 发送短信验证码成功
     * case02: sn错误数字签名失败
     * case03: 30s内发送多次，导致失败
     * case04: 发送次数超过5次，当天不可在发送
     * case05: 已经注册过外卖通的用户，发送验证码失败
     * case06: 已经注册过的用户level>=200，wmt方式发送验证码失败
     * case07: 已经注册过的闪电帮用户，wmt方式发送验证码失败
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void sendAuthCodeV1_HttpTest(final String caseId, final String description, final String mobile, final String type,
                                        final String ak, final String sk,final String sellerType,final String status,
                                        final String resultCode) throws UnsupportedEncodingException, InterruptedException {

        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String res_url = null;
        String url = HttpPostUrlEnum.SDG_SENDAUTHCODEV1_URL.getUrl();

        //生成数字签名
        codeParams.put("mobile",mobile);
        codeParams.put("type",type);
        codeParams.put("ak", ak);
        String sn = getByRequest(codeParams,sk);
        System.out.print("sn = " + sn + "\n");

        res_url = url + "?mobile=" + mobile + "&type=" + type + "&ak=" + ak + "&sn=" + sn;
        System.out.print("res_url = " + res_url + "\n");

        //case01: 发送短信验证码成功
        if(case_id.equals("01")) {

            try{

                res = httpClient.get(res_url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //清除redis缓存数据
                String sendCountKey = mobile + dateFormat.format(new Date());
                String lastSendTimeKey = mobile + LAST_SEND_TIME_KEY;
                redisUtils.redisDel(sendCountKey,0);
                redisUtils.redisDel(lastSendTimeKey,0);
            }
        }

        //case02: sn错误数字签名失败
        if(case_id.equals("02")){

            try{

                res_url = res_url + "a";
                res = httpClient.get(res_url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //清除redis缓存数据
                String sendCountKey = mobile + dateFormat.format(new Date());
                String lastSendTimeKey = mobile + LAST_SEND_TIME_KEY;
                redisUtils.redisDel(sendCountKey,0);
                redisUtils.redisDel(lastSendTimeKey,0);
            }

        }

        //case03: 30s内发送多次，导致失败
        if(case_id.equals("03")){

            try{

                for(int i=0;i<2;i++){
                    res = httpClient.get(res_url);
                    System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //清除redis缓存数据
                String sendCountKey = mobile + dateFormat.format(new Date());
                String lastSendTimeKey = mobile + LAST_SEND_TIME_KEY;
                redisUtils.redisDel(sendCountKey,0);
                redisUtils.redisDel(lastSendTimeKey,0);
            }

        }

        //case04: 发送次数超过5次，当天不可在发送
        if(case_id.equals("04")){

            try{

                for(int i=0;i<6;i++){
                    res = httpClient.get(res_url);
                    System.out.print("caseId" + caseId + ":result = " + res + "\n");
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //清除redis缓存数据
                String sendCountKey = mobile + dateFormat.format(new Date());
                String lastSendTimeKey = mobile + LAST_SEND_TIME_KEY;
                redisUtils.redisDel(sendCountKey,0);
                redisUtils.redisDel(lastSendTimeKey,0);
            }
        }

        //case05: 已经注册过外卖通的用户，发送验证码失败
        if(case_id.equals("05")) {
            //生成数字签名
            codeParams.clear();
            codeParams.put("mobile",mobile);
            codeParams.put("type",type);
            codeParams.put("ak", ak);
            codeParams.put("sellerType",sellerType);
            sn = getByRequest(codeParams,sk);
            System.out.print("sn = " + sn + "\n");

            res_url = url + "?mobile=" + mobile + "&type=" + type + "&ak=" + ak + "&sellerType=" + sellerType + "&sn=" + sn;
            System.out.print("res_url = " + res_url + "\n");
            try{

                res = httpClient.get(res_url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //清除redis缓存数据
                String sendCountKey = mobile + dateFormat.format(new Date());
                String lastSendTimeKey = mobile + LAST_SEND_TIME_KEY;
                redisUtils.redisDel(sendCountKey,0);
                redisUtils.redisDel(lastSendTimeKey,0);
            }
        }

        //case06: 已经注册过的用户level>200，wmt方式发送验证码失败
        if(case_id.equals("06")) {
            //生成数字签名
            codeParams.clear();
            codeParams.put("mobile",mobile);
            codeParams.put("type",type);
            codeParams.put("ak", ak);
            codeParams.put("sellerType",sellerType);
            sn = getByRequest(codeParams,sk);
            System.out.print("sn = " + sn + "\n");

            res_url = url + "?mobile=" + mobile + "&type=" + type + "&ak=" + ak + "&sellerType=" + sellerType + "&sn=" + sn;
            System.out.print("res_url = " + res_url + "\n");
            try{

                res = httpClient.get(res_url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //清除redis缓存数据
                String sendCountKey = mobile + dateFormat.format(new Date());
                String lastSendTimeKey = mobile + LAST_SEND_TIME_KEY;
                redisUtils.redisDel(sendCountKey,0);
                redisUtils.redisDel(lastSendTimeKey,0);
            }
        }

        //case07: 已经注册过的闪电帮用户，wmt方式发送验证码失败
        if(case_id.equals("07")) {
            //生成数字签名
            codeParams.clear();
            codeParams.put("mobile",mobile);
            codeParams.put("type",type);
            codeParams.put("ak", ak);
            codeParams.put("sellerType",sellerType);
            sn = getByRequest(codeParams,sk);
            System.out.print("sn = " + sn + "\n");

            res_url = url + "?mobile=" + mobile + "&type=" + type + "&ak=" + ak + "&sellerType=" + sellerType + "&sn=" + sn;
            System.out.print("res_url = " + res_url + "\n");
            try{

                res = httpClient.get(res_url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //清除redis缓存数据
                String sendCountKey = mobile + dateFormat.format(new Date());
                String lastSendTimeKey = mobile + LAST_SEND_TIME_KEY;
                redisUtils.redisDel(sendCountKey,0);
                redisUtils.redisDel(lastSendTimeKey,0);
            }
        }
    }
}
