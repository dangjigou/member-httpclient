package com.member.controller.httpUrl.user;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by jackson on 16/8/30.
 */
public class GetLoginStatus_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(GetLoginStatus_HttpTest.class);

    /**
     * 脚本说明：
     * case01: 正常用例，用户已登录
     * case02: 正常用例，用户未登录
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void getLoginStatus_HttpTest(final String caseId, final String description, final String mobile,final String role,
                                        final String password,String status,String resultCode){
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_GETLOGINSTATUS_URL.getUrl();
        System.out.println("url = " + url);

        //case01: 正常用例，用户已登录
        if(case_id.equals("01")) {
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

        //case02: 正常用例，用户未登录
        if(case_id.equals("02")) {
            try{

                res = httpClient.get(url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, false);
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //账号登出
                logOutAll();
            }
        }


    }
}
