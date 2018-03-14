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
 * Created by sunjing on 16/7/7.
 * 用于测试类目优化－类目初始化接口
 */
public class Init_v2_HttpTest extends MemberTestBase {
    static HttpClient httpClient = new HttpClient();
    Map<String, String> partnerParams = new HashMap<String, String>();

    /*
    *脚本说明：
    * 　case01：shopIds传一个，userId传一个，接口返回正确
    *   case02:shopIds传两个，userId传一个，接口返回正确
    *   case03:必填参数shopIds不传，userId=213002，接口返回错误
    *   case04:必填参数shopIds为null:shopIds=null，userId=213002,接口返回错误
    *   case05:shopIds为空：shopIds=，userId=213002,接口返回错误
    *   case06:shopIds为不存在：shopIds=1999999999，userId=213002,接口正确，返回空数据
    *   case07:userId不传，shopIds＝3693,接口返回正确
    *   case08:userId为null，userId=null，shopIds＝3693,参数类型不匹配
    *   case09:userId为空，userId＝，shopIds＝3693,接口返回正确
    *   case10:userId不存在：userId=-1，shopIds＝3693,参数类型不匹配
     */
    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void Init_v2_HttpTest(final String caseId, final String description, final String shopIds, final String userId) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_INITV2_URL.getUrl();
        if (case_id.equals("01")) { //case01：shopIds传一个，userId传一个，接口返回正确
            url = url + "?shopIds=" + shopIds + "&userId=" + userId;
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpSucess(res);
        } else if (case_id.equals("02")) {  //shopIds传两个，userId传一个，接口返回正确
            url = url + "?shopIds=" + shopIds + "&userId=" + userId;
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpSucess(res);
        } else if (case_id.equals("03")) {  //shopIds传两个，userId传一个，接口返回正确必填参数shopIds不传，userId=213002，接口返回错误
            url = url + "?userId=" + userId;
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.getHttpValue(res, "缺少参数");
        } else if (case_id.equals("04")) {  //必填参数shopIds为null，userId=213002,接口返回错误
            url = url + "?shopIds=" + shopIds + "&userId=" + userId;
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            //HttpUtils.checkHttpResCode(res,"0");
            HttpUtils.getHttpValue(res, "参数类型不匹配");
        } else if (case_id.equals("05")) {  //shopIds为空：shopIds=，userId=213002,接口返回错误
            url = url + "?shopIds=" + shopIds + "&userId=" + userId;
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            //HttpUtils.checkHttpResCode(res,"0");
            HttpUtils.getHttpValue(res, "参数错误");
        } else if (case_id.equals("06")) {  //shopIds不存在：shopIds=1999999999，userId=213002,接口正确，返回空数据
            url = url + "?shopIds=" + shopIds + "&userId=" + userId;
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            //HttpUtils.checkHttpResCode(res,"0");
            HttpUtils.checkHttpStatus(res, true);
        } else if (case_id.equals("07")) {  //userId不传，shopIds＝3693,接口返回正确
            url = url + "?shopIds=" + shopIds;
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            //HttpUtils.checkHttpResCode(res,"0");
            HttpUtils.checkHttpSucess(res);
        } else if (case_id.equals("08")) {  //userId为null，userId=null，shopIds＝3693,参数类型不匹配
            url = url + "?shopIds=" + shopIds + "&userId=" + userId;
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            //HttpUtils.checkHttpResCode(res,"0");
            HttpUtils.checkHttpStatus(res, false);
            HttpUtils.getHttpValue(res,"参数类型不匹配.");
        } else if (case_id.equals("09")) {  //userId为空，userId＝，shopIds＝3693,接口返回正确
            url = url + "?shopIds=" + shopIds + "&userId=" + userId;
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpSucess(res);
        } else if (case_id.equals("10")) {  //userId不存在：userId=-1，shopIds＝3693,参数类型不匹配
            url = url + "?shopIds=" + shopIds + "&userId=" + userId;
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, false);
            HttpUtils.getHttpValue(res, "参数类型不匹配.");

        }
    }
}
