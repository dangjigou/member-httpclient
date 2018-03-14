package com.member.controller.httpUrl.item;

import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;

import java.io.File;

/**
 * Created by jackson on 16/9/22.
 */
public class ImportLabelRelation_HttpTest extends MemberTestBase {
    /**
     * 脚本说明：
     * case01: 正常用例,导入标签绑定关系
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void importLabelRelation_HttpTest(final String caseId, final String description, String status, String resultCode,String fileName,String filePath) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_IMPORTLABELRELATION_URL.getUrl();
        System.out.println("url = " + url);

        //case01: 
        if (case_id.equals("01")) {
            try {

                //登录
                logIn("seller", "12345679000", "qwe123");

                filePath = new File(this.getClass().getResource("").getFile(),"../../../../../../../../src/test/resources/"+filePath).getCanonicalPath();
                System.out.println("filePath = " +filePath);
                res = httpClient.getHttpResponseMultipartFormDataByPost(url,fileName,filePath);
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

