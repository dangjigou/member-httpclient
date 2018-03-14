package com.member.controller.httpUrl.user;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import org.testng.annotations.Test;

/**
 * Created by jackson on 17/3/4.
 */
public class CreateOrganizeAdmin_HttpTest extends MemberTestBase {

    /**
     * 脚本说明：
     * case01: 正常用例,生成图文校验码
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void createOrganizeAdmin_HttpTest(final String caseId, final String description){
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_CREATEORGANIZEADMIN_URL.getUrl();
        System.out.println("url = " + url);

    }
}
