package com.member.controller.httpUrl.item.shelf2Controller;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import org.testng.annotations.Test;

/**
 * Created by jackson on 17/8/11.
 */
public class ShelfList_HttpTest extends MemberTestBase {

    /**
     * 脚本说明：
     * case01: 正常用例，集团获取前台类目列表
     * case02: 分支用例，withSkuCount为false
     * case03: 分支用例，shopId为null
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void shelfList_HttpTest(final String caseId, final String description, String status, String resultCode, String shopId, String withSkuCount) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.RETAIL_SHELF_LIST.getUrl();
        url = url + "?shopId=" + shopId + "&withSkuCount=" + withSkuCount;
        System.out.println("url = " + url);

        try{

            butlerLgoin("longying2017","lzh0605");

            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
            HttpUtils.checkHttpResCode(res, resultCode);
            HttpUtils.checkHttpEntryNotEmpty(res);

        }catch (Exception e) {
            System.err.println("脚本出现未知异常！" + e);
        } finally {
            //账号登出
            logOutAll();
        }


    }
}
