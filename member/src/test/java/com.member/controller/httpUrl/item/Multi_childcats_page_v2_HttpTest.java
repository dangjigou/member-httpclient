package com.member.controller.httpUrl.item;

import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;

/**
 * Created by jackson on 16/10/14.
 */
public class Multi_childcats_page_v2_HttpTest extends MemberTestBase {
    /**
     * 脚本说明：
     * case01:
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void multi_childcats_page_v2_HttpTest(final String caseId, final String description, String status, String resultCode,
                                                 String shopIds, String offset, String pageSize, String parentCatId, String childCatIds, String sortBy,
                                                 String userId) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_MULTI_CHILDCATS_PAGE_V2_URL.getUrl();
        url = url + "?shopIds=" + shopIds + "&offset=" +offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatIds=" +childCatIds
                + "&sortBy=" + sortBy + "&userId=" + userId;
        System.out.println("url = " + url);

        //case01: 
        if (case_id.equals("01")) {
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
}