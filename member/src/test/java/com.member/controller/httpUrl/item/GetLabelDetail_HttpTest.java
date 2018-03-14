package com.member.controller.httpUrl.item;

import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;

/**
 * Created by jackson on 16/9/23.
 */
public class GetLabelDetail_HttpTest extends MemberTestBase {
    /**
     * 脚本说明：
     * case01: 正常用例,获取标签树,containSpu等于0
     * case02: 正常用例,获取标签数,containSpu等于1
     * case03: 分支用例,labelId为空
     * case04: 分支用例,labelId为null
     * case05: 分支用例,labelId不存在
     * case06: 分支用例,containSpu为空
     * case07: 分支用例,containSpu为null
     * case08: 分支用例,containSpu为负数
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void getLabelDetail_HttpTest(final String caseId, final String description, String status, String resultCode,String labelId,String containSpu) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_GETLABELDETAIL_URL.getUrl();
        url = url + "?labelId=" +labelId + "&containSpu=" + containSpu;
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