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
 * Created by jackson on 16/8/24.
 */
public class UpdateMemberMobile_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(UpdateMemberMobile_HttpTest.class);
    Map<String,String> updateMemberMobileParams = new HashMap<String, String>();

    /**
     * 脚本说明：
     * case01: 正常用例，修改会员密码
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void updateMemberMobile_HttpTest(final String caseId,final String description,final String id,final String mobile,final String code,final String password){
        String res = null;
        logIn("seller", "12345679200","qwe123");
        final String case_id = MemberTestBase.getCaseId(caseId);
        String url = HttpPostUrlEnum.SDG_UPDATEMEMBERMOBILE_URL.getUrl();
        System.out.println("url = " + url);
        updateMemberMobileParams.put("id", id);
        updateMemberMobileParams.put("mobile", mobile);
        updateMemberMobileParams.put("code",code);
        updateMemberMobileParams.put("password",password);
        if(case_id.equals("01")){   //case01: 正常用例，添加会员成功
            res = httpClient.post(url, updateMemberMobileParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpSucess(res);
        }
    }

}
