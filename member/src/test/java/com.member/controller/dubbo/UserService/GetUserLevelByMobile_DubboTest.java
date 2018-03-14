package com.member.controller.dubbo.UserService;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;

/**
 * Created by jackson on 16/9/6.
 */
public class GetUserLevelByMobile_DubboTest extends MemberTestBase {
    /**
     * 脚本说明：
     * case01:
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void getUserLevelByMobile_DubboTest(final String caseId, final String description,String mobile) {
        int res = 0;

            try {

                //登录
                logIn("buyer", "12345679000", "1130");

                res = userService.getUserLevelByMobile(Long.valueOf(mobile));

                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                Assert.assertEquals(res, 1000);

            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
    }
}