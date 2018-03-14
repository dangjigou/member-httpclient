package com.member.controller.httpUrl.user;

import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;

/**
 * Created by jackson on 16/9/28.
 */
public class GetUserCommentByUserId_HttpTest extends MemberTestBase {
    /**
     * 脚本说明：
     * case01: 正常用例,获取用户意见反馈成功
     * case02: 分支用例,userId为空
     * case03: 分支用例,userId为null
     * case04: 分支用例,userId不存在
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void getUserCommentByUserId_HttpTest(final String caseId, final String description, String status, String resultCode,String userId) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_GETUSERCOMMENTBYUSERID_URL.getUrl();
        url = url + "?userId=" + userId;
        System.out.println("url = " + url);

            try {

                //登录
                logIn("buyer", "12345679000", "1130");

                res = httpClient.get(url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);

            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
        }
    }
}