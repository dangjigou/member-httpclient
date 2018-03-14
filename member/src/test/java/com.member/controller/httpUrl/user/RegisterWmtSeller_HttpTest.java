package com.member.controller.httpUrl.user;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.SdgDBUtils;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import com.member.test.utils.redis.RedisUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackson on 16/7/7.
 */
public class RegisterWmtSeller_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(SendAuthCodeV1_HttpTest.class);
//    static HttpClient httpClient = new HttpClient();
    Map<String,String> registerParams = new HashMap<String, String>();
    RedisUtils redisUtils = new RedisUtils();

    /**
     * 脚本说明：
     * case01: 注册外卖通seller账号成功
     * case02: 注册外卖通seller账号失败,闪电帮用户暂时不开放注册登录
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void registerWmtSeller_HttpTest(final String caseId, final String description, final String role, String mobile,
                                  String code, final String pwd, final String repwd){


        final String case_id = MemberTestBase.getCaseId(caseId);
        //case01: 注册外卖通seller账号成功
        if(case_id.equals("01")) {
            try {

                //登陆
                logIn("buyer", "12345679000", "1130");

                //获取正确验证码
                httpClient.get(HttpPostUrlEnum.SDG_GETREDISAUTHCODE_URL.getUrl() + "?mobile=" + mobile);
                code = redisUtils.redisGet("code->" + mobile, 0);
                System.out.print("code = " + code + "\n");

                //组装入参和初始化数据
                init(role,mobile,code,pwd,repwd);
                deleteOut(mobile);

                String res = httpClient.post(HttpPostUrlEnum.SDG_REGISTERWMTSELLER_URL.getUrl(), registerParams);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpSucess(res);
            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
             }finally {
                //账号登出
                logOutAll();
            }
        }

        //case02: 注册外卖通seller账号失败,闪电帮用户暂时不开放注册登录
        if(case_id.equals("02")) {
            try {

                //登陆
                logIn("buyer", "12345679000", "1130");

                //获取正确验证码
                httpClient.get(HttpPostUrlEnum.SDG_GETREDISAUTHCODE_URL.getUrl() + "?mobile=" + mobile);
                code = redisUtils.redisGet("code->" + mobile, 0);
                System.out.print("code = " + code + "\n");

                //组装入参和初始化数据
                init(role,mobile,code,pwd,repwd);
//                deleteOut(mobile);

                String res = httpClient.post(HttpPostUrlEnum.SDG_REGISTERWMTSELLER_URL.getUrl(), registerParams);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res,false);
                HttpUtils.checkHttpResCode(res,"10280");
            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //账号登出
                logOutAll();
            }
        }
    }

    private void init(String role,String mobile,String code,String pwd,String repwd) {
        //组装注册入參
        registerParams.put("role", role);
        registerParams.put("mobile", mobile);
        registerParams.put("code", code);
        registerParams.put("pwd", pwd);
        registerParams.put("repwd", repwd);
    }

    public void deleteOut(String mobile){
        //初始化注册数据，前置条件，注册前删除该条记录
//        SdgDBUtils.upDateDB("user","is_deleted " , " 0 " ,"mobile = " + "'" + mobile + "'");
        SdgDBUtils.deleteDB("user","mobile = " + "'" + mobile + "'","member");
        SdgDBUtils.deleteDB("user_extra","mobile = " + "'" + mobile + "'","member");
        //删除缓存uk、mu等
        redisUtils.redisDel("_uk->"+mobile,0);
        redisUtils.redisDel("m_uid."+ mobile,2);
        redisUtils.redisDel("m_u."+mobile,2);

    }


}
