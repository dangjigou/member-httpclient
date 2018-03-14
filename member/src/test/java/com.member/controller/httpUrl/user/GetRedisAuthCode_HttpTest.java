package com.member.controller.httpUrl.user;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;


/**
 * Created by jackson on 16/8/16.
 */
public class GetRedisAuthCode_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(GetRedisAuthCode_HttpTest.class);

    /**
     * 脚本说明：
     * case01: 正常用例，获取redis缓存中验证码成功
     * case02: 异常用例，必填参数mobile没传
     * case03: 异常用例，登录用户权限小于800，没有权限调用接口
     * case04: 异常用例，用户没有登录，不能调用接口
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void getRedisAuthCode_HttpTest(final String caseId, final String description, final String mobile,final String role,
                                          final String password,String status,String resultCode){

        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String res_url = null;
        String url = HttpPostUrlEnum.SDG_GETREDISAUTHCODE_URL.getUrl();
        res_url = url + "?mobile=" +mobile;
        System.out.println("res_url = " + res_url);

        if(case_id.equals("01")) {
            try{

                //登录
                logIn("buyer", "12345679000", "1130");

                res = httpClient.get(res_url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //账号登出
                logOutAll();
            }
        }

        //case02: 异常用例，必填参数mobile没传
        if(case_id.equals("02")) {
            try{

                //登录
                logIn("buyer", "12345679000", "1130");

                res = httpClient.get(url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //账号登出
                logOutAll();
            }
        }

        //case03: 异常用例，登录用户权限小于800，没有权限调用接口
        if(case_id.equals("03")) {
            try{

                //登录
                logIn("seller", "12345678500", "qwe123");

                res = httpClient.get(res_url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //账号登出
                logOutAll();
            }
        }

        //case04: 异常用例，用户没有登录，不能调用接口
        if(case_id.equals("04")) {
            try{

                res = httpClient.get(res_url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //账号登出
                logOutAll();
            }
        }

    }

}
