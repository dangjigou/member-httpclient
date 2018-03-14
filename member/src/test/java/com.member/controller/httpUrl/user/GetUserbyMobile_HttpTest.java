package com.member.controller.httpUrl.user;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by jackson on 16/8/26.
 */
public class GetUserbyMobile_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(GetUserbyMobile_HttpTest.class);

    /**
     * 脚本说明：
     * case01: 正常用例，登录用户权限大于500，获取当前登陆用户信息
     * case02: 异常用例，登录用户权限小于500，调用接口失败
     * case03: 异常用例，mobile不存在
     * case04: 异常用例，mobile没传
     * case05: 异常用例，mobile为空
     **/

    //根据用户手机号取用户信息
    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void getUserbyMobile_HttpTest(final String caseId, final String description, final String mobile,final String role,
                                         final String password,String userMobile){
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_GETUSERBYMOBILE_URL.getUrl();
        System.out.println("url = " + url);
        if(case_id.equals("01")) {

            //登录
            logIn(role, mobile,password);
            res = httpClient.get(url + "?mobile=" + userMobile);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpSucess(res);

            //退出登录
            logOut(role);
        }else if(case_id.equals("02")) {

            //登录
            logIn(role, mobile,password);
            res = httpClient.get(url + "?mobile=" + userMobile);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, false);
            HttpUtils.checkHttpResCode(res, "10260");

            //退出登录
            logOut(role);
        }else if(case_id.equals("03")) {

            //登录
            logIn(role, mobile,password);
            res = httpClient.get(url + "?mobile=" + userMobile);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, false);
            HttpUtils.checkHttpResCode(res, "10219");

            //退出登录
            logOut(role);
        }else if(case_id.equals("04")) {

            //登录
            logIn(role, mobile,password);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, false);
            HttpUtils.checkHttpResCode(res, "10216");

            //退出登录
            logOut(role);
        }else if(case_id.equals("05")) {

            //登录
            logIn(role, mobile,password);
            res = httpClient.get(url + "?mobile=" + userMobile);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, false);
            HttpUtils.checkHttpResCode(res, "10216");

            //退出登录
            logOut(role);
        }
    }

}
