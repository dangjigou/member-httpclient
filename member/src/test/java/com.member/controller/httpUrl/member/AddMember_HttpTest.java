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
public class AddMember_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(AddMember_HttpTest.class);
    Map<String,String> memberParams = new HashMap<String, String>();

    /**
     * 脚本说明：
     * case01: 正常用例，添加会员成功
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void addMember_HttpTest(final String caseId,final String description,final String shopId,final String mobile,final String code,
                                   final String password,final String name,final String sex,final String address){

        String res = null;
        logIn("seller", "12345679200","qwe123");
        final String case_id = MemberTestBase.getCaseId(caseId);
        String url = HttpPostUrlEnum.SDG_ADDMEMBER_URL.getUrl();
        System.out.println("url = " + url);
        memberParams.put("shopId",shopId);
        memberParams.put("mobile",mobile);
//        memberParams.put("code",code);
        memberParams.put("password",password);
        memberParams.put("name",name);
        memberParams.put("sex",sex);
        memberParams.put("address",address);
        if(case_id.equals("01")){   //case01: 正常用例，添加会员成功
            res = httpClient.post(url, memberParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpSucess(res);
        }
    }

    private void init(String shopId,String mobile,String code,String password,String name,String sex,String address){
        //组装添加会员入參
        memberParams.put("shopId",shopId);
        memberParams.put("mobile",mobile);
//        memberParams.put("code",code);
        memberParams.put("password",password);
        memberParams.put("name",name);
        memberParams.put("sex",sex);
        memberParams.put("address",address);

        //初始化注册数据，前置条件，注册前删除该条记录
    }

}
