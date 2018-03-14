package com.member.controller.httpUrl.item;

import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jackson on 16/10/14.
 */
public class GetFineQualityItems_HttpTest extends MemberTestBase {
    /**
     * 脚本说明：
     * case01:
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void getFineQualityItems_HttpTest(final String caseId, final String description, String status, String resultCode,
                                             String shopIds, String count, String renderMark) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_GETFINEQUALITYITEMS_URL.getUrl();
        url = url + "?shopIds=" + shopIds + "&count=" + count + "&renderMark=" + renderMark;
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