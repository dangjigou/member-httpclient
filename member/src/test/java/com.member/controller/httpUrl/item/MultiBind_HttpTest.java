package com.member.controller.httpUrl.item;

import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;

/**
 * Created by jackson on 16/10/10.
 */
public class MultiBind_HttpTest extends MemberTestBase {
    /**
     * 脚本说明：
     * case01: 正常用例，spu绑定标签成功
     * case02: 分支用例，spuIds为空
     * case03: 分支用例，spuIds为null
     * case04: 分支用例，spuIds为为负数
     * case05: 分支用例，spuIds为不存在
     * case06: 分支用例，labelIds为空
     * case07: 分支用例，labelIds为null
     * case08: 分支用例，labelIds为负数
     * case09: 分支用例，labelIds为不存在
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void multiBind_HttpTest(final String caseId, final String description, String status, String resultCode,String spuIds,String labelIds) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_MLTIBIND_URL.getUrl();
        url = url + "?spuIds=" + spuIds + "&labelIds=" + labelIds;
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