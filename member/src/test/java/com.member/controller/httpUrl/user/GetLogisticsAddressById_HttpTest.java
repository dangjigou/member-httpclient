package com.member.controller.httpUrl.user;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by jackson on 16/8/29.
 */
public class GetLogisticsAddressById_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(GetLogisticsAddressById_HttpTest.class);

    /**
     * 脚本说明：
     * case01: 正常用例，根据ID获取地址成功
     * case02: 异常用例，非自己地址获取失败
     * case03: 异常用例，地址ID不存在
     * case04: 异常用例，用户没有登录，获取地址失败
     * case05: 异常用例，必传参数addressId没有传
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void getLogisticsAddressById_HttpTest(String caseId ,String description ,String role ,String addressId,String status,String resultCode){
        String res= null;
        String res_url = null;
        final String case_id = MemberTestBase.getCaseId(caseId);
        String url = HttpPostUrlEnum.SDG_GETLOGISTICSADDRESSBYID_URL.getUrl();
        res_url = url + "?addressId=" + addressId;
        System.out.println("res_url = " + url);

        //case01: 正常用例，根据ID获取地址成功
        if(case_id.equals("01")) {
            try {

                //登陆
                logIn("seller", "12345679200", "qwe123");

                res = httpClient.get(res_url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);
            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOut(role);
            }
        }


        //case02: 异常用例，非自己地址获取失败
        if(case_id.equals("02")){
            try{

                //登陆
                logIn("seller", "12345679200", "qwe123");

                res = httpClient.get(res_url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //账号登出
                logOut(role);
            }
        }


        //case03: 异常用例，地址ID不存在
        if(case_id.equals("03")){
            try{

                //登陆
                logIn("seller", "12345679200","qwe123");

                res = httpClient.get(res_url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //账号登出
                logOut(role);
            }
        }

        //case04: 异常用例，用户没有登录，获取地址失败
        if(case_id.equals("04")){
            try{

                res = httpClient.get(res_url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //账号登出
                logOutAll();
            }
        }

        //case05: 异常用例，必传参数addressId没有传
        if(case_id.equals("05")){
            try{

                //登陆
                logIn("seller", "12345679200", "qwe123");

                res = httpClient.get(url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);

            }catch (Exception e){
                System.err.println("脚本出现未知异常！" + e);
            }finally {
                //账号登出
                logOutAll();
            }
        }
    }

}
