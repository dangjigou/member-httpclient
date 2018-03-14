package com.member.controller.httpUrl.user;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.SdgDBUtils;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpClient;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import com.member.test.utils.redis.ClearRedisForMobile;
import com.member.test.utils.redis.RedisUtils;
import com.member.test.utils.rsaUtils.RSAUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackson on 16/5/31.
 */
public class LoginForWmt_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(LoginForWmt_HttpTest.class);
    static HttpClient httpClient = new HttpClient();
    Map<String,String> loginParams = new HashMap<String, String>();
    RedisUtils redisUtils = new RedisUtils();

    /**
    * 脚本说明：
     * case01: 外卖通账号密码登录成功
     * case02: 必传参数mobile没有传，登录失败
     * case03: 半小时内，密码错误登录超过5次，账号被锁，登录失败
     * case04: 用户未注册，用户密码登录失败
     * case05: 闪电帮账号无法登录外卖通,登录失败
     * case06: 买家密码RSA加密登录成功
     * case07: 买家密码RSA加密错误登录失败
     * case08: 密码password为空，登录失败
     * case09: 密码password为null，登录失败
     * case10: 手机号码mobile为空,登录失败
     * case11: 手机号码mobile为null,登录失败
     * case12: 必传参数role没有传，登录成功
     * case13: 手机号码role为空，登录失败
     * case14: 手机号码role为null，登录失败
     * case15: 大KA集团账号登录，登录成功
     * case16: 大KA集团账号密码错误，登录失败
     * case17: 大KA集团账号不存在，登录失败
    **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void loginForWmt_HttpTest(final String caseId ,final String description,final String role,final String mobile,
                               String code, String password,final String iosToken,final String getuiToken,final String umengToken,
                               final String mipushToken,String encrypt,String status,String resultCode) {

        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_LOGINFORWMT_URL.getUrl();

        //组装入参
        init(role, mobile, code, password, iosToken, getuiToken, umengToken, mipushToken,encrypt);

        //case02: 必传参数mobile没有传，登录失败
        if (case_id.equals("02")) {
            try {
                loginParams.clear();
                loginParams.put("role", role);
                loginParams.put("code", code);
                loginParams.put("password", password);
                loginParams.put("iosToken", iosToken);
                loginParams.put("getuiToken", getuiToken);
                loginParams.put("umengToken", umengToken);
                loginParams.put("mipushToken", mipushToken);
                res = httpClient.post(url, loginParams);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);
            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
                ClearRedisForMobile.clearRedisForMobile(mobile);
            }
        }else

        //case12: 必传参数role没有传，登录成功,默认为seller登录
        if (case_id.equals("12")) {
            try {
                loginParams.clear();
                loginParams.put("mobile",mobile);
                loginParams.put("code", code);
                loginParams.put("password", password);
                loginParams.put("iosToken", iosToken);
                loginParams.put("getuiToken", getuiToken);
                loginParams.put("umengToken", umengToken);
                loginParams.put("mipushToken", mipushToken);
                res = httpClient.post(url, loginParams);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);
            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
                ClearRedisForMobile.clearRedisForMobile(mobile);
            }
        }else

        //case06: 买家密码RSA加密登录成功
            if (case_id.equals("06")) {
                try {
                    //公钥加密
                    password = RSAUtils.publicKeyEncryption(password);
                    loginParams.clear();
                    loginParams.put("mobile", mobile);
                    loginParams.put("role", role);
                    loginParams.put("code", code);
                    loginParams.put("password", password);
                    loginParams.put("iosToken", iosToken);
                    loginParams.put("getuiToken", getuiToken);
                    loginParams.put("umengToken", umengToken);
                    loginParams.put("mipushToken", mipushToken);
                    loginParams.put("encrypt",encrypt);        //encrypt字段来区分是否RSA加密
                    res = httpClient.post(url, loginParams);
                    System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                    HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                    HttpUtils.checkHttpResCode(res, resultCode);
                } catch (Exception e) {
                    System.err.println("脚本出现未知异常！" + e);
                } finally {
                    //清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
                    ClearRedisForMobile.clearRedisForMobile(mobile);
                }
            }else

                //case07: 买家密码RSA加密错误登录失败(encrypt=1,但是公钥加密错误)
                if (case_id.equals("07")) {
                    try {
                        //公钥加密
                        password = RSAUtils.publicKeyEncryption(password) + "test";    //加密的密码加个“test”，登录失败
                        loginParams.clear();
                        loginParams.put("mobile", mobile);
                        loginParams.put("role", role);
                        loginParams.put("code", code);
                        loginParams.put("password", password);
                        loginParams.put("iosToken", iosToken);
                        loginParams.put("getuiToken", getuiToken);
                        loginParams.put("umengToken", umengToken);
                        loginParams.put("mipushToken", mipushToken);
                        loginParams.put("encrypt", encrypt);        //encrypt字段来区分是否RSA加密
                        res = httpClient.post(url, loginParams);
                        System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                        HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                        HttpUtils.checkHttpResCode(res, resultCode);
                    } catch (Exception e) {
                        System.err.println("脚本出现未知异常！" + e);
                    } finally {
                        //清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
                        ClearRedisForMobile.clearRedisForMobile(mobile);
                    }
                }else

        //case03: 半小时内，买家验证码错误超过5次，账号被锁，登录失败
        if (case_id.equals("03")) {
            try {
                 for (int i = 0; i < 6; i++) {
                     res = httpClient.post(url, loginParams);
                     System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                     try {
                         Thread.sleep(2000);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
                 HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                 HttpUtils.checkHttpResCode(res,resultCode);
            } catch (Exception e) {
                 System.err.println("脚本出现未知异常！" + e);
            } finally {
                 //清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
                 ClearRedisForMobile.clearRedisForMobile(mobile);
            }
        }else


    {

            try {
                res = httpClient.post(url, loginParams);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);
            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
                ClearRedisForMobile.clearRedisForMobile(mobile);
            }
        }
    }

    public void init(final String role,final String mobile,
                     final String code, final String password,final String iosToken,final String getuiToken ,
                     final String umengToken,final String mipushToken,String encrypt){

        //组装登录入參
        loginParams.put("role",role);
        loginParams.put("mobile",mobile);
        loginParams.put("code",code);
        loginParams.put("password",password);
        loginParams.put("iosToken",iosToken);
        loginParams.put("getuiToken",getuiToken);
        loginParams.put("umengToken",umengToken);
        loginParams.put("mipushToken", mipushToken);
        loginParams.put("encrypt",encrypt);

    }

    public void deleteUserForMobile(String mobile){
        //先检查数据库中是否存在，存在的话先删除
        SdgDBUtils.deleteDB("user", "mobile = " + "'" + mobile + "'","member");
        SdgDBUtils.deleteDB("user_extra","mobile = " + "'" + mobile + "'","member");
        //删除缓存uk、mu等
        redisUtils.redisDel("_uk->"+mobile,0);
        redisUtils.redisDel("m_uid."+ mobile,2);
        redisUtils.redisDel("m_u."+mobile,2);
    }

    public int checkDBForMobile(String mobile){
        int count = SdgDBUtils.selectCountDB("user","mobile = " + "'" + mobile + "'","member");
        return count;
    }
}
