package com.member.controller.httpUrl.user;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpClient;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import com.member.test.utils.rsaUtils.RSAUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackson on 16/6/6.
 */
public class ResetBuyerPWD_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(ResetBuyerPWD_HttpTest.class);
    static HttpClient httpClient = new HttpClient();
    Map<String,String> resetBuyerPWDParams = new HashMap<String, String>();
    /**
     * 脚本说明：
     * case01: 正确mobile、code、pwd，修改密码成功
     * case02: 密码中带有不识别的特殊字符，修改密码成功
     * case03: 验证码错误，修改密码失败
     * case04: 密码小于6位，修改密码失败
     * case05: 密码大于16位，修改密码失败
     * case06: 买家密码RSA加密，修改密码成功
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void resetBuyerPWD_HttpTest(final String caseId, final String description, final String mobile,
                                       final String code, String pwd,String encrypt,String status,String resultCode){
        final String case_id = MemberTestBase.getCaseId(caseId);
        String url = HttpPostUrlEnum.SDG_RESETBUYERPWD_URL.getUrl();
        resetBuyerPWDParams.put("mobile",mobile);
        resetBuyerPWDParams.put("code",code);
        resetBuyerPWDParams.put("pwd",pwd);

        if(case_id.equals("06")){

            try {
                //公钥加密
                pwd = RSAUtils.publicKeyEncryption(pwd);
            } catch (Exception e) {
                e.printStackTrace();
            }
            resetBuyerPWDParams.clear();
            resetBuyerPWDParams.put("mobile", mobile);
            resetBuyerPWDParams.put("code", code);
            resetBuyerPWDParams.put("pwd", pwd);
            resetBuyerPWDParams.put("encrypt",encrypt);
            String res = httpClient.post(url, resetBuyerPWDParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
            HttpUtils.checkHttpResCode(res,resultCode);
        }else {
            String res = httpClient.post(url, resetBuyerPWDParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
            HttpUtils.checkHttpResCode(res,resultCode);
        }

    }
}
