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
public class UpdateMember_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(UpdateMember_HttpTest.class);
    Map<String,String> updateMemberParams = new HashMap<String, String>();

    /**
     * 脚本说明：
     * case01: 正常用例，修改会员
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void updateMember_HttpTest(final String caseId,final String description,final String id,final String userId,final String shopId,final String mobile,final String code,
                                      final String password,final String name,final String sex,final String address){
        String res = null;
        logIn("seller", "12345678921","qwe123");
        final String case_id = MemberTestBase.getCaseId(caseId);
        String url = HttpPostUrlEnum.SDG_UPDATEMEMBER_URL.getUrl();
        System.out.println("url = " + url);
        updateMemberParams.put("id",id);
        updateMemberParams.put("userId",userId);
        updateMemberParams.put("shopId",shopId);
        updateMemberParams.put("mobile",mobile);
        updateMemberParams.put("code",code);
        updateMemberParams.put("password",password);
        updateMemberParams.put("name",name);
        updateMemberParams.put("sex",sex);
        updateMemberParams.put("address",address);
        if(case_id.equals("01")){   //case01: 正常用例，添加会员成功
            res = httpClient.post(url, updateMemberParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpSucess(res);
        }

    }

}
