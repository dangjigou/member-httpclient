package com.member.controller.httpUrl.item;

import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;

/**
 * Created by jackson on 16/9/26.
 */
public class GetBoxUnitItems_HttpTest extends MemberTestBase {
    /**
     * 脚本说明：
     * case01: 正常用例,获取整箱购商品成功
     * case02: 分支用例,shopIds为空
     * case03: 分支用例,count为0
     * case04: 分支用例,shopIds为null
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void getBoxUnitItems_HttpTest(final String caseId, final String description, String status, String resultCode,String shopIds,String count) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_GETBOXUNITITEMS_URL.getUrl();
        url = url + "?shopIds=" + shopIds + "&count=" + count;
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