package com.member.controller.httpUrl.user;

import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;

/**
 * Created by jackson on 16/9/7.
 */
public class GetUserAddressCount_HttpTest extends MemberTestBase {
    /**
     * 脚本说明：
     * case01: 正常用例，获取地址数量成功
     * case02: 异常用例，用户没有登录，获取地址失败
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void getUserAddressCount_HttpTest(final String caseId, final String description,String role,String mobile,String password) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_GETUSERADDRESSCOUNT_URL.getUrl();
        System.out.println("url = " + url);

        ////case01: 正常用例，获取地址数量成功
        if (case_id.equals("01")) {
            try {

                //登录
                logIn("buyer", "12345679000", "1130");

                res = httpClient.get(url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpSucess(res);

            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
        }

        //case02: 异常用例，用户没有登录，获取地址数量失败
        if (case_id.equals("02")) {
            try {

                res = httpClient.get(url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, false);
                HttpUtils.checkHttpResCode(res, "10212");

            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
        }
    }
}