package com.member.controller.httpUrl.user;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.SdgDBUtils;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpClient;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import com.member.test.utils.redis.ClearRedisForMobile;
import com.member.test.utils.redis.RedisUtils;
import com.member.test.utils.rsaUtils.RSAUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackson on 16/5/31.
 */
public class Login_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(Login_HttpTest.class);
    static HttpClient httpClient = new HttpClient();
    Map<String,String> loginParams = new HashMap<String, String>();
    RedisUtils redisUtils = new RedisUtils();

    /**
    * 脚本说明：
     * case01: 买家正确通用验证码登录成功
     * case02: 必传参数mobile没有传，登录失败
     * case03: 必传参数role没有传，后端处理默认role=buyer，登录成功
     * case04: role传值不对，登录失败(role:buyer/seller/courier/crm)
     * case05: code和password都为空，买家登录失败
     * case06: code和password都为null，买家登录失败
     * case07: 买家错误通用验证码登录失败
     * case08: 买家密码登录成功
     * case09: 买家密码登录，密码错误，登录失败
     * case10: 密码pwd中带有不识别表情，买家登录成功
     * case11: 半小时内，买家验证码错误超过5次，账号被锁，登录失败
     * case12: 半小时内，买家密码错误登录超过5次，账号被锁，登录失败
     * case13: 用户未注册，买家密码登录失败
     * case14: 用户未注册，买家验证码登录成功，且数据库新增数据
     * case15: 买家密码RSA加密登录成功
     * case16: 买家密码RSA加密错误登录失败
     * case17: 密码password为空，卖家登录失败
     * case18: 密码password为null，卖家登录失败
     * case19: mobile对应用户不存在，卖家登录失败
     * case20: 密码正确，卖家登录成功
     * case21: 未设置密码，登录失败
     * case22: 黑名单用户，登录失败
     * case23: 权限小于500，crm登录失败
     * case24: 权限大于500，crm登录失败
     * case25: 密码为空，crm登录失败
     * case26: 外卖通卖家账号无法登录闪电帮,登录失败
    **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void login_HttpTest(final String caseId ,final String description,final String role,final String mobile,
                               String code, String password,final String iosToken,final String getuiToken,final String umengToken,
                               final String mipushToken,String encrypt,String status,String resultCode) {

        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_LOGIN_URL.getUrl();

        //组装入参
        init(role, mobile, code, password, iosToken, getuiToken, umengToken, mipushToken,encrypt);

        //case02: 必传参数mobile没有传，登录失败
        if (case_id.equals("02")) {
            try {
                loginParams.clear();
                loginParams.put("role", role);
                loginParams.put("code", code);
                loginParams.put("password", password);
                loginParams.put("iosToken", iosToken);
                loginParams.put("getuiToken", getuiToken);
                loginParams.put("umengToken", umengToken);
                loginParams.put("mipushToken", mipushToken);
                res = httpClient.post(url, loginParams);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);
            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
                ClearRedisForMobile.clearRedisForMobile(mobile);
            }
        }else

        //case03: 必传参数role没有传，后端处理默认role=buyer，登录成功
        if (case_id.equals("03")) {
            try {
                loginParams.clear();
                loginParams.put("mobile", mobile);
                loginParams.put("code", code);
                loginParams.put("password", password);
                loginParams.put("iosToken", iosToken);
                loginParams.put("getuiToken", getuiToken);
                loginParams.put("umengToken", umengToken);
                loginParams.put("mipushToken", mipushToken);
                res = httpClient.post(url, loginParams);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);
            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
                ClearRedisForMobile.clearRedisForMobile(mobile);
            }
        }else

        //case11: 半小时内，买家验证码错误超过5次，账号被锁，登录失败
        if (case_id.equals("11")) {
            try {
                for (int i = 0; i < 6; i++) {
                    res = httpClient.post(url, loginParams);
                    System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);
            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
                ClearRedisForMobile.clearRedisForMobile(mobile);
            }
        }else

        //case12: 半小时内，密码错误登录超过5次，账号被锁，登录失败
        if (case_id.equals("12")) {
            try {
                for (int i = 0; i < 6; i++) {
                    res = httpClient.post(url, loginParams);
                    System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);
            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
                ClearRedisForMobile.clearRedisForMobile(mobile);
            }
        }else

        //case13: 用户未注册，密码登录失败
        if (case_id.equals("13")) {
            try {

                //先检查数据库中是否存在，存在的话先删除
               deleteUserForMobile(mobile);

                res = httpClient.post(url, loginParams);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);
            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
                ClearRedisForMobile.clearRedisForMobile(mobile);
            }
        }else

        //case14: 用户未注册，买家验证码登录成功，且数据库新增数据
        if (case_id.equals("14")) {
            try {

                //登录
                logIn("buyer", "12345679000", "1130");

                //先检查数据库中是否存在，存在的话先删除
                deleteUserForMobile(mobile);

                //获取验证码
                httpClient.get(HttpPostUrlEnum.SDG_GETREDISAUTHCODE_URL.getUrl() + "?mobile=" + mobile);
                System.out.println("mobile = " + mobile);
                code = redisUtils.redisGet("code->" + mobile, 0);
                System.out.print("code = " + code + "\n");

                //重新组装数据入参
                loginParams.clear();
                init(role, mobile, code, password, iosToken, getuiToken, umengToken, mipushToken, encrypt);

                res = httpClient.post(url, loginParams);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);
                HttpUtils.checkHttpStatus(String.valueOf(checkDBForMobile(mobile) == 1),true);
            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
                ClearRedisForMobile.clearRedisForMobile(mobile);
            }
        }else

        //case15: 买家密码RSA加密登录成功
        if (case_id.equals("15")) {
            try {
                //公钥加密
                password = RSAUtils.publicKeyEncryption(password);
                loginParams.clear();
                loginParams.put("mobile", mobile);
                loginParams.put("role", role);
                loginParams.put("code", code);
                loginParams.put("password", password);
                loginParams.put("iosToken", iosToken);
                loginParams.put("getuiToken", getuiToken);
                loginParams.put("umengToken", umengToken);
                loginParams.put("mipushToken", mipushToken);
                loginParams.put("encrypt",encrypt);        //encrypt字段来区分是否RSA加密
                res = httpClient.post(url, loginParams);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);
            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
                ClearRedisForMobile.clearRedisForMobile(mobile);
            }
        }else

        //case16: 买家密码RSA加密错误登录失败(encrypt=1,但是公钥加密错误)
        if (case_id.equals("16")) {
            try {
                //公钥加密
                password = RSAUtils.publicKeyEncryption(password) + "test";    //加密的密码加个“test”，登录失败
                loginParams.clear();
                loginParams.put("mobile", mobile);
                loginParams.put("role", role);
                loginParams.put("code", code);
                loginParams.put("password", password);
                loginParams.put("iosToken", iosToken);
                loginParams.put("getuiToken", getuiToken);
                loginParams.put("umengToken", umengToken);
                loginParams.put("mipushToken", mipushToken);
                loginParams.put("encrypt",encrypt);        //encrypt字段来区分是否RSA加密
                res = httpClient.post(url, loginParams);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);
            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
                ClearRedisForMobile.clearRedisForMobile(mobile);
            }

        }else {
            try {
                res = httpClient.post(url, loginParams);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res,resultCode);
            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
                ClearRedisForMobile.clearRedisForMobile(mobile);
            }
        }



    }

    public void init(final String role,final String mobile,
                     final String code, final String password,final String iosToken,final String getuiToken ,
                     final String umengToken,final String mipushToken,String encrypt){

        //组装登录入參
        loginParams.put("role",role);
        loginParams.put("mobile",mobile);
        loginParams.put("code",code);
        loginParams.put("password",password);
        loginParams.put("iosToken",iosToken);
        loginParams.put("getuiToken",getuiToken);
        loginParams.put("umengToken",umengToken);
        loginParams.put("mipushToken", mipushToken);
        loginParams.put("encrypt",encrypt);

    }

    public void deleteUserForMobile(String mobile){
        //先检查数据库中是否存在，存在的话先删除
        SdgDBUtils.deleteDB("user", "mobile = " + "'" + mobile + "'","member");
        SdgDBUtils.deleteDB("user_extra","mobile = " + "'" + mobile + "'","member");
        //删除缓存uk、mu等
        redisUtils.redisDel("_uk->"+mobile,0);
        redisUtils.redisDel("m_uid."+ mobile,2);
        redisUtils.redisDel("m_u."+mobile,2);
    }

    public int checkDBForMobile(String mobile){
        int count = SdgDBUtils.selectCountDB("user","mobile = " + "'" + mobile + "'","member");
        return count;
    }
}
