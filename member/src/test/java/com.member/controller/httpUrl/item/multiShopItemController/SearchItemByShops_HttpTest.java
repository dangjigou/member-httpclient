package com.member.controller.httpUrl.item.multiShopItemController;

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
 * Created by jackson on 17/8/21.
 */
public class SearchItemByShops_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(SearchItemByShops_HttpTest.class);
    static HttpClient httpClient = new HttpClient();
    Map<String,String> searchItemByShopsParams = new HashMap<String, String>();

    /**
     * 脚本说明：
     * case01: 正常用例，按单城市查询
     * case02: 正常用例，按单店铺查询
     * case03: 正常用例，按多城市查询
     * case04: 正常用例，按多店铺查询
     * case05: 正常用例，查询经销商品manageType＝normal
     * case06: 正常用例，查询联营商品manageType＝joint
     * case07: 正常用例，查询联营商品manageType＝joint
     * case08: 正常用例，查询标准商品soldType＝0
     * case09: 正常用例，查询标准商品soldType＝1
     * case10: 正常用例，查询出售中商品
     * case11: 正常用例，查询为上架商品
     * case12: 正常用例，查询售罄商品
     * case13: 正常用例，按单条码查询barcodes
     * case14: 正常用例，按多条码查询barcodes
     * case15: 正常用例，按单商家编码查询SkuCodes
     * case16: 正常用例，按多商家编码查询SkuCodes
     * case17: 分支用例，organizeId不存在
     * case18: 分支用例，organizeId为负数
     * case19: 分支用例，organizeId为null
     * case20: 分支用例，organizeId为空
     * case21: 分支用例，areaType为0
     * case22: 分支用例，areaType为空
     * case23: 分支用例，areaType为null
     * case24: 分支用例，codeType为0
     * case25: 分支用例，codeType为空
     * case26: 分支用例，codeType为null
     * case27: 分支用例，cityCodes为汉字
     * case28: 分支用例，cityCodes为空
     * case29: 分支用例，cityCodes为null
     * case30: 分支用例，cityCodes不存在
     * case31: 分支用例，shopIds不存在
     * case32: 分支用例，shopIds为空
     * case33: 分支用例，shopIds为null
     * case34: 分支用例，barcodes不存在
     * case35: 分支用例，barcodes为空
     * case36: 分支用例，barcodes为null
     * case37: 分支用例，SkuCodes不存在
     * case38: 分支用例，SkuCodes为空
     * case39: 分支用例，SkuCodes为null
     * case40: 分支用例，sortField＝quantity，sortOrder=desc
     * case41: 分支用例，sortField＝quantity，sortOrder=asc
     * case42: 分支用例，sortField=hahaha，sortOrder=desc
     * case43: 分支用例，sortField=quantity，sortOrder=hahaha
     * case44: 分支用例，sortField为null
     * case45: 分支用例，sortOrder为null
     * case46: 分支用例，soldType为2
     * case47: 分支用例，soldType为null
     * case48: 分支用例，manageType=hahaha
     * case49: 分支用例，manageType为null
     * case50: 分支用例，pageNo=2
     * case51: 分支用例，pageSize=100
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void searchItemByShops_HttpTest(final String caseId, final String description, String result_status, String resultCode, String organizeId, String areaType, String codeType,
                                           String cityCodes, String shopIds,String barcodes, String skuCodes, String status, String sortField, String sortOrder, String soldType, String manageType,
                                           String pageNo, String pageSize) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.RETAIL_SEARCH_BY_SHOPS.getUrl();
        System.out.println("url = " + url);

        try{

            butlerLgoin("longying2017","lzh0605");

            searchItemByShopsParams.clear();
            searchItemByShopsParams.put("organizeId", organizeId);
            searchItemByShopsParams.put("areaType", areaType);
            searchItemByShopsParams.put("codeType", codeType);
            searchItemByShopsParams.put("cityCodes", cityCodes);
            searchItemByShopsParams.put("shopIds", shopIds);
            searchItemByShopsParams.put("barcodes", barcodes);
            searchItemByShopsParams.put("skuCodes", skuCodes);
            searchItemByShopsParams.put("status", status);
            searchItemByShopsParams.put("sortField", sortField);
            searchItemByShopsParams.put("sortOrder", sortOrder);
            searchItemByShopsParams.put("soldType", soldType);
            searchItemByShopsParams.put("manageType", manageType);
            searchItemByShopsParams.put("pageNo", pageNo);
            searchItemByShopsParams.put("pageSize", pageSize);

            res = httpClient.post(url, searchItemByShopsParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(result_status));
            HttpUtils.checkHttpResCode(res, resultCode);

        }catch (Exception e) {
            System.err.println("脚本出现未知异常！" + e);
        } finally {
            //账号登出
            logOutAll();
        }


    }
}
