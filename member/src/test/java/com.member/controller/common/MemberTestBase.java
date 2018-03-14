package com.member.controller.common;


import com.member.test.utils.DateUtil;
import com.member.test.utils.httputils.HttpClient;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import com.shandiangou.itemcenter.open.api.ItemListApiService;
import com.shandiangou.member.service.UserService;
import com.shandiangou.member.session.SdgSessionMock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by jackson on 16/05/28.
 */

@ContextConfiguration(locations = "classpath:testContext.xml")
public class MemberTestBase extends AbstractTestNGSpringContextTests{
//public class MemberTestBase {


    private static final String VERIFY_PASSWORD_ACTIVITY = "verifyMobilePassword";

    private static final String VERIFY_PASSWORD_ACTIVITY_DAY = "verifyMobilePassword_day";

    public static HttpClient httpClient = new HttpClient();

    @Resource
    public UserService userService;

    @Resource
    public ItemListApiService itemListApiService;

    //mock CRM用户Session
    public void crmSession(Long mobile , Long userId){
        SdgSessionMock sdgSession = new SdgSessionMock();
        sdgSession.initCRMUser(mobile, userId);
    }

    public void buyerSession(Long mobile , Long userId){
        SdgSessionMock sdgSession = new SdgSessionMock();
        sdgSession.initBuyer(mobile, userId);
    }

    public void initSession(){
        SdgSessionMock sdgSessionMock = new SdgSessionMock();
        sdgSessionMock.init();
    }

    /**
     *账户登录
     */
    public void logIn(String role,String mobile,String password){
        //入参为空，直接返回，不做登录
        if (role.isEmpty()||mobile.isEmpty()||password.isEmpty()){
            return;
        }
        //登陆入參
        Map<String, String> loginParams = new HashMap<String,String>();

        //组装登陆账号入參
        loginParams.put("role",role);
        loginParams.put("mobile",mobile);
        if (role.equals("buyer")){
            loginParams.put("code",password);
        }else{
            loginParams.put("password",password);
        }

        //账户登录
        String loginResult = httpClient.post(HttpPostUrlEnum.SDG_LOGIN_URL.getUrl(), loginParams);

        //校验登陆结果
        HttpUtils.checkHttpSucess(loginResult);

        try {
            //持续集成缓存加载
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(">>>>>帐户登录成功");
    }


    /**
     *账户登录
     */
    public void logInForCrm(String role,String mobile,String password){
        //入参为空，直接返回，不做登录
        if (role.isEmpty()||mobile.isEmpty()||password.isEmpty()){
            return;
        }
        //登陆入參
        Map<String, String> loginParams = new HashMap<String,String>();

        //组装登陆账号入參
        loginParams.put("role",role);
        loginParams.put("mobile",mobile);
        if (role.equals("buyer")){
            loginParams.put("code",password);
        }else{
            loginParams.put("password",password);
        }

        //账户登录
        String loginResult = httpClient.post(HttpPostUrlEnum.CRM_LOGIN_URL.getUrl(), loginParams);

        //校验登陆结果
        HttpUtils.checkHttpSucess(loginResult);

        try {
            //持续集成缓存加载
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(">>>>>帐户登录成功");
    }

    /**
     *一号管家账户登录
     */
    public void butlerLgoin(String mobile,String password){
        //入参为空，直接返回，不做登录
        if (mobile.isEmpty()||password.isEmpty()){
            return;
        }
        //登陆入參
        Map<String, String> loginParams = new HashMap<String,String>();

        //组装登陆账号入參
        loginParams.put("mobile",mobile);
        loginParams.put("password",password);

        //账户登录
        String loginResult = httpClient.post(HttpPostUrlEnum.RETAIL_LOGIN.getUrl(), loginParams);

        //校验登陆结果
        HttpUtils.checkHttpSucess(loginResult);

        try {
            //持续集成缓存加载
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(">>>>>一号管家帐户登录成功");
    }

    /**
     *一号管家账户预发环境登录
     */
    public void butlerLgoinGray(String mobile,String password){
        //入参为空，直接返回，不做登录
        if (mobile.isEmpty()||password.isEmpty()){
            return;
        }
        //登陆入參
        Map<String, String> loginParams = new HashMap<String,String>();

        //组装登陆账号入參
        loginParams.put("mobile",mobile);
        loginParams.put("password",password);

        //账户登录
        String loginResult = httpClient.post(HttpPostUrlEnum.RETAIL_LOGIN_GRAYKA.getUrl(), loginParams);

        //校验登陆结果
        HttpUtils.checkHttpSucess(loginResult);

        try {
            //持续集成缓存加载
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(">>>>>一号管家帐户登录成功");
    }

    /**
     * 账户登出
     *
     */
    public void logOut(String role){
        //如果为空，直接返回
        if (role.isEmpty()){
            return;
        }
        //登陆入參
        Map<String, String> logoutParams = new HashMap<String,String>();

        //组装登陆账号入參
        logoutParams.put("role",role);

        //账户登录
        String logoutResult = httpClient.post(HttpPostUrlEnum.SDG_LOGOUT_URL.getUrl(), logoutParams);

        //校验登陆结果
        HttpUtils.checkHttpSucess(logoutResult);

        try {
            //持续集成缓存加载
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(">>>>>帐户退出成功");
    }

    /**
     * 账户登出
     *
     */
    public void logOutAll(){

        //账户登录
        String logoutResult = httpClient.get(HttpPostUrlEnum.SDG_LOGOUTALL_URL.getUrl());

        //校验登陆结果
        HttpUtils.checkHttpSucess(logoutResult);

        try {
            //持续集成缓存加载
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(">>>>>帐户退出成功");
    }

    //用例id编号
    public static String CASE_ID;


    /**
     * 根据caseId获取caseId的编号（最后两位）
     * @param caseId
     * @return
     */
    public static String getCaseId(String caseId) {
        int length = caseId.length();
        return caseId.substring(length - 2, length);

    }

    /**
     *
     * @return
     */
    protected String getRedomId() {
        return DateUtil.getLongDateString(new Date()) + "-" + new Random().nextInt(1000000);
    }


}
