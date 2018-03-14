package com.member.controller.httpUrl.user;

import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackson on 16/9/8.
 */
public class ResetPwd_HttpTest extends MemberTestBase {

    Map<String,String> resetPWDParams = new HashMap<String, String>();
    /**
     * 脚本说明：
     * case01: 正确mobile、role、code、pwd、repwd，role为seller，修改密码成功
     * case02: 密码中带有不识别的特殊字符，修改密码成功
     * case03: 验证码错误，修改密码失败
     * case04: pwd密码小于6位，修改密码失败
     * case05: pwd密码大于16位，修改密码失败
     * case06: mobile为空，修改密码失败
     * case07: mobile没有传，修改密码失败
     * case08: mobile为null，修改密码失败
     * case09: code为空，修改密码失败
     * case10: code为null，修改密码失败
     * case11: role为空，修改密码失败
     * case12: role为null，修改密码失败
     * case13: pwd为空，修改密码失败
     * case14: pwd和repwd不一致，修改密码失败
     * case15: role为crm，修改密码成功
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void resetPwd_HttpTest(final String caseId, final String description,String mobile,String role,String code,
                                  String pwd,String repwd, String encrypt,String status,String resultCode) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_RESETPWD_URL.getUrl();
        System.out.println("url = " + url);
        resetPWDParams.put("mobile", mobile);
        resetPWDParams.put("role",role);
        resetPWDParams.put("code",code);
        resetPWDParams.put("pwd", pwd);
        resetPWDParams.put("repwd",repwd);

        //case01: 正确mobile、role、code、pwd、repwd，修改密码成功
        if (case_id.equals("07")) {
            try {
                resetPWDParams.clear();
                resetPWDParams.put("role",role);
                resetPWDParams.put("code",code);
                resetPWDParams.put("pwd", pwd);
                resetPWDParams.put("repwd",repwd);

                res = httpClient.post(url,resetPWDParams);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);

            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
        }else {
            try {

                res = httpClient.post(url,resetPWDParams);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);

            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
        }

        //case02: 密码中带有不识别的特殊字符，修改密码成功
        if (case_id.equals("02")) {
            try {

                res = httpClient.post(url,resetPWDParams);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpSucess(res);

            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
        }
    }
}