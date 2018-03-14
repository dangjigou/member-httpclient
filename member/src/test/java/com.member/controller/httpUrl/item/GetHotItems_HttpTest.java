package com.member.controller.httpUrl.item;

import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;

/**
 * Created by jackson on 16/9/29.
 */
public class GetHotItems_HttpTest extends MemberTestBase {
    /**
     * 脚本说明：
     * case01: 正常用例,获取热销排行商品
     * case02: 分支用例,shopIds为空
     * case03: 分支用例,shopIds为null
     * case04: 分支用例,shopIds不存在
     * case05: 分支用例,count为空
     * case06: 分支用例,count为null
     * case07: 分支用例,count为负数
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void getHotItems_HttpTest(final String caseId, final String description, String status, String resultCode,String shopIds,String count) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_GETHOTITEMS_URL.getUrl();
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