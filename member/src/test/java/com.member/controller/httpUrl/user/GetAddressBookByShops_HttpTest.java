package com.member.controller.httpUrl.user;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by jackson on 16/6/28.
 */
public class GetAddressBookByShops_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(GetAddressBookByShops_HttpTest.class);

    /**
     * 脚本说明：
     * case01: 正常用例，获取地标下用户地址
     * case02: 必填字段shopIds没有传
     * case03: 必填字段shopIds为null
     * case04: 必填字段landmarkId没有传
     * case05: 必填字段landmarkId为null
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void getAddressBookByShops_HttpTest(String caseId ,String description ,String role , String mobile ,
                                               String password , String landmarkId ,String shopIds,String status,String resultCode){
//        buyerSession(12345679000L,929476L);
        logIn(role, mobile,password);
        String res = null;
        final String case_id = MemberTestBase.getCaseId(caseId);
        String url = HttpPostUrlEnum.SDG_GETADDRESSBOOKBYSHOPS_URL.getUrl();
        if(case_id.equals("01")){
            url = url + "?landmarkid=" + landmarkId + "&shopids=" + shopIds;
            System.out.print("url = " + url + "\n\r");
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
            HttpUtils.checkHttpResCode(res,resultCode);
        }else if(case_id.equals("02")){
            url = url + "?landmarkid=" + landmarkId;
            System.out.print("url = " + url + "\n\r");
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
            HttpUtils.checkHttpResCode(res,resultCode);
        }else if(case_id.equals("03")){
            url = url + "?landmarkid=" + landmarkId + "&shopids=" + shopIds;
            System.out.print("url = " + url + "\n\r");
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
            HttpUtils.checkHttpResCode(res,resultCode);
        }else if(case_id.equals("04")){
            url = url + "?&shopids=" + shopIds;
            System.out.print("url = " + url + "\n\r");
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
            HttpUtils.checkHttpResCode(res,resultCode);
        }else if(case_id.equals("05")){
            url = url + "?landmarkid=" + landmarkId + "&shopids=" + shopIds;
            System.out.print("url = " + url + "\n\r");
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
            HttpUtils.checkHttpResCode(res,resultCode);
        }

    }
}
