package com.member.controller.httpUrl.item.skuShelf2Controller;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpClient;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackson on 17/8/11.
 */
public class ShelfUpdate_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(ShelfUpdate_HttpTest.class);
    static HttpClient httpClient = new HttpClient();
    Map<String,String> shelfUpdateParams = new HashMap<String, String>();

    /**
     * 脚本说明：
     * case01: 正常用例，集团更新前台类目列表
     * case02: 分支用例，name超过8个字符长度
     * case03: 分支用例，id不正确
     * case04: 分支用例，不属于该集团的类目，不允许修改
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void shelfUpdate_HttpTest(final String caseId, final String description, String status, String resultCode, String id, String name, String organizeId) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.RETAIL_SHELF_UPDATE.getUrl();
        System.out.println("url = " + url);

        try{

            butlerLgoin("longying2017","lzh0605");

            shelfUpdateParams.clear();
            shelfUpdateParams.put("id", id);
            shelfUpdateParams.put("name", name);
            shelfUpdateParams.put("organizeId", organizeId);

            res = httpClient.post(url, shelfUpdateParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
            HttpUtils.checkHttpResCode(res, resultCode);

        }catch (Exception e) {
            System.err.println("脚本出现未知异常！" + e);
        } finally {
            //账号登出
            logOutAll();
        }


    }
}
