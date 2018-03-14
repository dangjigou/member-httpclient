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
public class UpdateMemberPassword_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(UpdateMemberPassword_HttpTest.class);
    Map<String,String> updateMemberPasswordParams = new HashMap<String, String>();

    /**
     * 脚本说明：
     * case01: 正常用例，修改会员密码
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void updateMemberPassword_HttpTest(final String caseId,final String description,final String id,final String mobile,final String password,final String oldPassword){
        String res = null;
        logIn("seller", "12345679200","qwe123");
        final String case_id = MemberTestBase.getCaseId(caseId);
        String url = HttpPostUrlEnum.SDG_UPDATEMEMBERPASSWORD_URL.getUrl();
        System.out.println("url = " + url);
        updateMemberPasswordParams.put("id",id);
        updateMemberPasswordParams.put("mobile", mobile);
        updateMemberPasswordParams.put("password",password);
        updateMemberPasswordParams.put("oldPassword",oldPassword);
        if(case_id.equals("01")){   //case01: 正常用例，添加会员成功
            res = httpClient.post(url, updateMemberPasswordParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpSucess(res);
        }

    }

}
