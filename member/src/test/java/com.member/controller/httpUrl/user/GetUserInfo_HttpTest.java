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
public class GetUserInfo_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(GetUserInfo_HttpTest.class);

    /**
     * 脚本说明：
     * case01: 正常用例，获取当前登陆用户信息
     * case02: 异常用例，用户没有登录，调用接口失败
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void getUserInfo_HttpTest(final String caseId, final String description, final String mobile,final String role,
                                     final String password){

        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_GETUSERINFO_URL.getUrl();
        System.out.println("url = " + url);
        if(case_id.equals("01")) {
            logIn(role, mobile,password);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpSucess(res);
            logOut(role);
        }else if(case_id.equals("02")) {
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpStatus(res,false);
            HttpUtils.checkHttpResCode(res,"10212");
        }
    }
}
