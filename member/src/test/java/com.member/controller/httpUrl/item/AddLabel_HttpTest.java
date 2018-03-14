package com.member.controller.httpUrl.item;

import com.member.test.utils.SdgDBUtils;
import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;

/**
 * Created by jackson on 16/9/23.
 */
public class AddLabel_HttpTest extends MemberTestBase {
    /**
     * 脚本说明：
     * case01: 正常用例,添加标签成功
     * case02: 分支用例,labelName为空
     * case03: 分支用例,labelName已经存在
     * case04: 分支用例,typeId不存在或被删除
     * case05: 分支用例,typeId为空
     * case06: 分支用例,typeId为null
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void addLabel_HttpTest(final String caseId, final String description, String status, String resultCode,String labelName,String typeId) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_ADDLABEL_URL.getUrl();
        url = url + "?labelName=" + labelName + "&typeId=" + typeId;
        System.out.println("url = " + url);

        if(case_id.equals("03")){
            try {

                //登录
                logIn("buyer", "12345679000", "1130");
                res = httpClient.get(url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);
                HttpUtils.checkHttpResMessage(res, "标签已经存在!");

            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
        }

        else if(case_id.equals("04")){
            try {

                //登录
                logIn("buyer", "12345679000", "1130");
                res = httpClient.get(url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);
                HttpUtils.checkHttpResMessage(res, "标签类型不存在!");

            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
        }

        else  {
            try {

                //先删除,再添加
                SdgDBUtils.deleteDB("itemcenter.i_item_label", "label_name = " + "'" + labelName + "'","itemcenter");

                //登录
                logIn("buyer", "12345679000", "1130");

                res = httpClient.get(url);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpStatus(res, Boolean.parseBoolean(status));
                HttpUtils.checkHttpResCode(res, resultCode);

            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
        }
    }
}