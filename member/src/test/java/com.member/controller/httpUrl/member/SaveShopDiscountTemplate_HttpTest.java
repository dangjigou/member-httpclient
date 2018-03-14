package com.member.controller.httpUrl.member;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackson on 16/8/22.
 */
public class SaveShopDiscountTemplate_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(SaveShopDiscountTemplate_HttpTest.class);
    Map<String,String> shopDiscountTemplateParams = new HashMap<String, String>();

    /**
     * 脚本说明：
     * case01: 正常用例，保存店铺优惠规则
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void saveShopDiscountTemplate_HttpTest(final String caseId,final String description,
                                                  String shopId,String allowDiscount,String discount,String allowSdgMember){
        String res = null;
        logIn("seller", "12345679200","qwe123");
        final String case_id = MemberTestBase.getCaseId(caseId);
        String url = HttpPostUrlEnum.SDG_SAVESHOPDISCOUNTTEMPLATE_URL.getUrl();
        System.out.println("url = " + url + "\n\r");
        shopDiscountTemplateParams.put("shopId", shopId);
        shopDiscountTemplateParams.put("allowDiscount", allowDiscount);
        shopDiscountTemplateParams.put("discount", discount);
        shopDiscountTemplateParams.put("allowSdgMember",allowSdgMember);
        if(case_id.equals("01")){   //case01: 正常用例，获取店铺优惠规则
            res = httpClient.post(url, shopDiscountTemplateParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpSucess(res);
        }
    }
}
