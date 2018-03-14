package com.member.controller.httpUrl.item;

import org.junit.Assert;
import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;

/**
 * Created by jackson on 16/9/20.
 */
public class Myfrequentbuy_HttpTest extends MemberTestBase {
    /**
     * 脚本说明：
     * case01: 正常用例,shopIds为一个,获取我常买商品
     * case02: 正常用例,shopIds为多个,获取我常买商品
     * case03: 分支用例，shopIds为空
     * case04: 分支用例，shopIds为null
     * case05: 分支用例，shopIds未传
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void myfrequentbuy_HttpTest(final String caseId, final String description, String status, String resultCode,String shopIds) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_MYFREQUENTBUY_URL.getUrl();
        System.out.println("url = " + url);

        if(case_id.equals("04")){
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

        else {
            try {

                //登录
                logIn("seller", "12345679000", "qwe123");

                url = url + "?shopIds=" + shopIds;
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