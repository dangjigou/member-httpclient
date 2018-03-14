package com.member.controller.httpUrl.user;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * Created by jackson on 16/7/1.
 */
public class GetUserAddressList_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(GetUserAddressList_HttpTest.class);

    /**
     * 脚本说明：
     * case01: 正常用例，获取地址成功
     * case02: 异常用例，用户没有登录，获取地址失败
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void getUserAddressList_HttpTest(String caseId ,String description ,String role , String mobile ,
                                            String password){

        String res = null;
        final String case_id = MemberTestBase.getCaseId(caseId);
        String url = HttpPostUrlEnum.SDG_GETUSERADDRESSLIST_URL.getUrl();

        //case01: 正常用例，获取地址成功
        if(case_id.equals("01")){
            try{

                //登录
                logIn(role, mobile,password);

                res = httpClient.get(url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpSucess(res);
                logOutAll();
            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
        }

        //case02: 异常用例，用户没有登录，获取地址失败
        if(case_id.equals("02")){
            try{

                res = httpClient.get(url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, false);
                HttpUtils.checkHttpResCode(res, "10212");
                logOutAll();
            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
        }

    }
}
