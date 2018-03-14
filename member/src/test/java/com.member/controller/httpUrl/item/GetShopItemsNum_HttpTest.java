package com.member.controller.httpUrl.item;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpClient;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunjing on 16/7/21.
 * 用于测试闪电帮类目优化－根据商品状态获取指定店铺商品数量
 */
public class GetShopItemsNum_HttpTest extends MemberTestBase {
    static HttpClient httpClient = new HttpClient();
    Map<String, String> partnerParams = new HashMap<String, String>();

    /*
    *脚本说明：
    * 　case01：shopId必传1002474，status必传online，接口返回正确
    *   case02:必填参数shopId不传，status=online，接口返回错误
    *   case03:必填参数status不传，shopId=1002474，接口返回错误
    *   case04:必填参数shopIds为null:shopIds=null，userId=213002,接口返回错误
    *   case05:shopIds为空：shopIds=，userId=213002,接口返回错误
    *   case06:shopIds为不存在：shopIds=1999999999，userId=213002,接口正确，返回空数据
    *   case07:userId不传，shopIds＝3693,接口返回正确
    *   case08:userId为null，userId=null，shopIds＝3693,参数类型不匹配
    *   case09:userId为空，userId＝，shopIds＝3693,接口返回正确
    *   case10:userId不存在：userId=-1，shopIds＝3693,参数类型不匹配
     */
    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void getShopItemsNum_HttpTest(final String caseId, final String description, final String shopId, final String status) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDB_GETSHOPITEMSNUM_URL.getUrl();
        //登录
        logIn("buyer", "12345679000", "1130");

        System.err.print("url是："+url);
        if (case_id.equals("01")) { //case01：shopIds必传，status必传，接口返回正确
            url = url + "?shopId=" + shopId + "&status=" + status;
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpSucess(res);
        } else if (case_id.equals("02")) {  //必填参数shopIds不传，userId=online，接口返回错误
            url = url + "?status=" + status;
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.getHttpValue(res,"传递店铺id错误");
        } else if (case_id.equals("03")) {  //必填参数status不传，shopIds=1002474，接口返回错误
            url = url + "?shopId=" + shopId;
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.getHttpValue(res, "缺少参数");
        }
        //账号登出
        logOutAll();
    }
}
