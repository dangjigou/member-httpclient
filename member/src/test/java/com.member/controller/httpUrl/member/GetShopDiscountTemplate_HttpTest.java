package com.member.controller.httpUrl.member;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by jackson on 16/8/22.
 */
public class GetShopDiscountTemplate_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(GetShopDiscountTemplate_HttpTest.class);

    /**
     * 脚本说明：
     * case01: 正常用例，获取店铺优惠规则
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void getShopDiscountTemplate_HttpTest(final String caseId,final String description,String shopId){
        String res = null;
        logIn("seller", "12345679200","qwe123");
        final String case_id = MemberTestBase.getCaseId(caseId);
        String url = HttpPostUrlEnum.SDG_GETSHOPDISCOUNTTEMPLATE_URL.getUrl();
        System.out.println("url = " + url + "\n\r");
        if(case_id.equals("01")){   //case01: 正常用例，获取店铺优惠规则
            url = url + "?shopId=" + shopId;
            System.out.print("url = " + url + "\n\r");
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpSucess(res);
        }
    }

}
