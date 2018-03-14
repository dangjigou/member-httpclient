package com.member.controller.httpUrl.user;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpClient;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunjing on 16/6/30.
 * 用于测试商家申请加盟接口
 */
public class SubmitPartner_HttpTest extends MemberTestBase {
    static HttpClient httpClient = new HttpClient();
    Map<String, String> partnerParams = new HashMap<String, String>();

    /*
    *脚本说明：
    * 　case01：商家申请加盟成功
    *   case02:必填参数applyName没有传，申请失败
    *   case03:必填参数mobile没有传，申请失败
    *   case04:必填参数shopName没有传，申请失败
    *   case05:必填参数shopType没有传，申请失败
    *   case06:必填参数province没有传，申请失败
    *   case07:必填参数city没有传，申请失败
    *   case10:必填参数district没有传，申请失败
    *   case11:必填参数address没有传，申请失败
    *   case12:必填参数hasDelivery没有传，申请失败
    *   case13:必填参数openingTime没有传，申请失败
    *   case14:必填参数hasBusinessLicense没有传，申请失败
    *   case15:必填参数hasFoodLicense没有传，申请失败
     */
    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void submitPartner_HttpTest(final String caseId, final String description, final String applyName, final String mobile, final String shopName, final String shopType,
                                       final String province, final String city, final String district, final String address,
                                       final String hasDelivery, final String openingTime, final String hasBusinessLicense,
                                       final String hasFoodLicense) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_SUBMITPARTNER_URL.getUrl();
        partnerParams.put("applyName", applyName);
        partnerParams.put("mobile", mobile);
        partnerParams.put("shopName", shopName);
        partnerParams.put("shopType", shopType);
        partnerParams.put("province", province);
        partnerParams.put("city", city);
        partnerParams.put("district", district);
        partnerParams.put("address", address);
        partnerParams.put("hasDelivery", hasDelivery);
        partnerParams.put("openingTime", openingTime);
        partnerParams.put("hasBusinessLicense", hasBusinessLicense);
        partnerParams.put("hasFoodLicense", hasFoodLicense);
        if (case_id.equals("01")) { //case01：商家申请加盟成功
            res = httpClient.post(url, partnerParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpSucess(res);
        }else if (case_id.equals("02")){  //case02:必填参数applyName没有传，申请失败
            partnerParams.clear();
            //partnerParams.put("applyName", applyName);
            partnerParams.put("mobile", mobile);
            partnerParams.put("shopName", shopName);
            partnerParams.put("shopType", shopType);
            partnerParams.put("province", province);
            partnerParams.put("city", city);
            partnerParams.put("district", district);
            partnerParams.put("address", address);
            partnerParams.put("hasDelivery", hasDelivery);
            partnerParams.put("openingTime", openingTime);
            partnerParams.put("hasBusinessLicense", hasBusinessLicense);
            partnerParams.put("hasFoodLicense", hasFoodLicense);
            res = httpClient.post(url, partnerParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpResCode(res, "10241");
        }else if (case_id.equals("03")){  //case03:必填参数mobile没有传，申请失败
            partnerParams.clear();
            partnerParams.put("applyName", applyName);
            //partnerParams.put("mobile", mobile);
            partnerParams.put("shopName", shopName);
            partnerParams.put("shopType", shopType);
            partnerParams.put("province", province);
            partnerParams.put("city", city);
            partnerParams.put("district", district);
            partnerParams.put("address", address);
            partnerParams.put("hasDelivery", hasDelivery);
            partnerParams.put("openingTime", openingTime);
            partnerParams.put("hasBusinessLicense", hasBusinessLicense);
            partnerParams.put("hasFoodLicense", hasFoodLicense);
            res = httpClient.post(url, partnerParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpResCode(res, "10241");
        }else if (case_id.equals("04")){  //case04:必填参数shopName没有传，申请失败
            partnerParams.clear();
            partnerParams.put("applyName", applyName);
            partnerParams.put("mobile", mobile);
            //partnerParams.put("shopName", shopName);
            partnerParams.put("shopType", shopType);
            partnerParams.put("province", province);
            partnerParams.put("city", city);
            partnerParams.put("district", district);
            partnerParams.put("address", address);
            partnerParams.put("hasDelivery", hasDelivery);
            partnerParams.put("openingTime", openingTime);
            partnerParams.put("hasBusinessLicense", hasBusinessLicense);
            partnerParams.put("hasFoodLicense", hasFoodLicense);
            res = httpClient.post(url, partnerParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpResCode(res, "10241");
        }else if (case_id.equals("05")){  //case05:必填参数shopType没有传，申请失败
            partnerParams.clear();
            partnerParams.put("applyName", applyName);
            partnerParams.put("mobile", mobile);
            partnerParams.put("shopName", shopName);
            //partnerParams.put("shopType", shopType);
            partnerParams.put("province", province);
            partnerParams.put("city", city);
            partnerParams.put("district", district);
            partnerParams.put("address", address);
            partnerParams.put("hasDelivery", hasDelivery);
            partnerParams.put("openingTime", openingTime);
            partnerParams.put("hasBusinessLicense", hasBusinessLicense);
            partnerParams.put("hasFoodLicense", hasFoodLicense);
            res = httpClient.post(url, partnerParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpResCode(res, "10241");
        }else if (case_id.equals("06")){  //case06:必填参数province没有传，申请失败
            partnerParams.clear();
            partnerParams.put("applyName", applyName);
            partnerParams.put("mobile", mobile);
            partnerParams.put("shopName", shopName);
            partnerParams.put("shopType", shopType);
            //partnerParams.put("province", province);
            partnerParams.put("city", city);
            partnerParams.put("district", district);
            partnerParams.put("address", address);
            partnerParams.put("hasDelivery", hasDelivery);
            partnerParams.put("openingTime", openingTime);
            partnerParams.put("hasBusinessLicense", hasBusinessLicense);
            partnerParams.put("hasFoodLicense", hasFoodLicense);
            res = httpClient.post(url, partnerParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpResCode(res, "10241");
        }else if (case_id.equals("07")){  //case07:必填参数city没有传，申请失败
            partnerParams.clear();
            partnerParams.put("applyName", applyName);
            partnerParams.put("mobile", mobile);
            partnerParams.put("shopName", shopName);
            partnerParams.put("shopType", shopType);
            partnerParams.put("province", province);
            //partnerParams.put("city", city);
            partnerParams.put("district", district);
            partnerParams.put("address", address);
            partnerParams.put("hasDelivery", hasDelivery);
            partnerParams.put("openingTime", openingTime);
            partnerParams.put("hasBusinessLicense", hasBusinessLicense);
            partnerParams.put("hasFoodLicense", hasFoodLicense);
            res = httpClient.post(url, partnerParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpResCode(res, "10241");
        }else if (case_id.equals("10")){  //case10:必填参数district没有传，申请失败
            partnerParams.clear();
            partnerParams.put("applyName", applyName);
            partnerParams.put("mobile", mobile);
            partnerParams.put("shopName", shopName);
            partnerParams.put("shopType", shopType);
            partnerParams.put("province", province);
            partnerParams.put("city", city);
            //partnerParams.put("district", district);
            partnerParams.put("address", address);
            partnerParams.put("hasDelivery", hasDelivery);
            partnerParams.put("openingTime", openingTime);
            partnerParams.put("hasBusinessLicense", hasBusinessLicense);
            partnerParams.put("hasFoodLicense", hasFoodLicense);
            res = httpClient.post(url, partnerParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpResCode(res, "10241");
        }else if (case_id.equals("11")){  //case11:必填参数address没有传，申请失败
            partnerParams.clear();
            partnerParams.put("applyName", applyName);
            partnerParams.put("mobile", mobile);
            partnerParams.put("shopName", shopName);
            partnerParams.put("shopType", shopType);
            partnerParams.put("province", province);
            partnerParams.put("city", city);
            partnerParams.put("district", district);
            //partnerParams.put("address", address);
            partnerParams.put("hasDelivery", hasDelivery);
            partnerParams.put("openingTime", openingTime);
            partnerParams.put("hasBusinessLicense", hasBusinessLicense);
            partnerParams.put("hasFoodLicense", hasFoodLicense);
            res = httpClient.post(url, partnerParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpResCode(res, "10241");
        }else if (case_id.equals("12")){  //case12:必填参数hasDelivery没有传，申请失败
            partnerParams.clear();
            partnerParams.put("applyName", applyName);
            partnerParams.put("mobile", mobile);
            partnerParams.put("shopName", shopName);
            partnerParams.put("shopType", shopType);
            partnerParams.put("province", province);
            partnerParams.put("city", city);
            partnerParams.put("district", district);
            partnerParams.put("address", address);
            //partnerParams.put("hasDelivery", hasDelivery);
            partnerParams.put("openingTime", openingTime);
            partnerParams.put("hasBusinessLicense", hasBusinessLicense);
            partnerParams.put("hasFoodLicense", hasFoodLicense);
            res = httpClient.post(url, partnerParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpResCode(res, "10241");
        }else if (case_id.equals("13")){  //case13:必填参数openingTime没有传，申请失败
            partnerParams.clear();
            partnerParams.put("applyName", applyName);
            partnerParams.put("mobile", mobile);
            partnerParams.put("shopName", shopName);
            partnerParams.put("shopType", shopType);
            partnerParams.put("province", province);
            partnerParams.put("city", city);
            partnerParams.put("district", district);
            partnerParams.put("address", address);
            partnerParams.put("hasDelivery", hasDelivery);
            //partnerParams.put("openingTime", openingTime);
            partnerParams.put("hasBusinessLicense", hasBusinessLicense);
            partnerParams.put("hasFoodLicense", hasFoodLicense);
            res = httpClient.post(url, partnerParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpResCode(res, "10241");
        }else if (case_id.equals("14")){  //case14:必填参数hasBusinessLicense没有传，申请失败
            partnerParams.clear();
            partnerParams.put("applyName", applyName);
            partnerParams.put("mobile", mobile);
            partnerParams.put("shopName", shopName);
            partnerParams.put("shopType", shopType);
            partnerParams.put("province", province);
            partnerParams.put("city", city);
            partnerParams.put("district", district);
            partnerParams.put("address", address);
            partnerParams.put("hasDelivery", hasDelivery);
            partnerParams.put("openingTime", openingTime);
            //partnerParams.put("hasBusinessLicense", hasBusinessLicense);
            partnerParams.put("hasFoodLicense", hasFoodLicense);
            res = httpClient.post(url, partnerParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpResCode(res, "10241");
        }else if (case_id.equals("15")){  //case15:必填参数hasFoodLicense没有传，申请失败
            partnerParams.clear();
            partnerParams.put("applyName", applyName);
            partnerParams.put("mobile", mobile);
            partnerParams.put("shopName", shopName);
            partnerParams.put("shopType", shopType);
            partnerParams.put("province", province);
            partnerParams.put("city", city);
            partnerParams.put("district", district);
            partnerParams.put("address", address);
            partnerParams.put("hasDelivery", hasDelivery);
            partnerParams.put("openingTime", openingTime);
            partnerParams.put("hasBusinessLicense", hasBusinessLicense);
            //partnerParams.put("hasFoodLicense", hasFoodLicense);
            res = httpClient.post(url, partnerParams);
            System.out.print("caseId:" + caseId + ":result = " + res + "\n");
            HttpUtils.checkHttpResCode(res, "10241");
        }
    }
}
