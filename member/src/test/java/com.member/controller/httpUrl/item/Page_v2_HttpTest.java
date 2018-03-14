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
 * Created by sunjing on 16/7/8.
 * 用于测试类目优化－类目下的商品分页
 */
public class Page_v2_HttpTest extends MemberTestBase {
    static HttpClient httpClient = new HttpClient();
    Map<String, String> partnerParams = new HashMap<String, String>();

    /*
    *脚本说明：
    * 　case01：shopIds=3693，其他参数正确,接口正确，返回数据
    *   case02:shopIds=3693,4499，其他参数正确,接口正确，返回数据
    *   case03:必填参数shopIds不传，其他参数正确,接口返回错误
    *   case04:shopIds为null：shopIds=null，其他参数正确,接口返回错误
    *   case05:shopIds为空：shopIds=，其他参数正确,接口返回错误
    *   case06:shopIds为不存在：shopIds=1999999999，其他参数正确,接口正确，返回空数据
    *   case07:offset=1，其他参数正确,正常返回数据
    *   case08:offset不传，其他参数正确,不传就默认1,正常返回数据
    *   case09:offset为offset=null，其他参数正确,默认1,正常返回数据
    *   case10:offset为空，offset=，其他参数正确,默认1,正常返回数据
    *   case11:offset=-1，其他参数正确，默认1，正常返回数据
    *   case12:sortBy=0，其他参数正确，接口正确，返回数据
    *   case13:sortBy=1，其他参数正确，接口正确，返回数据
    *   case14:sortBy=2，其他参数正确，接口正确，返回数据
    *   case15:sortBy=3，其他参数正确，接口正确，返回数据
    *   case16:sortBy不传，其他参数正确，不传默认为0，接口正确，返回数据
    *   case17:sortBy=4，其他参数正确，不等于0~3，默认为0，接口正确，返回数据
    *   case18:user_id不传，parentCatId=0，其他参数正确，接口正确，返回空数据
    *   case19:user_id为null，user_id=null，其他参数正确，接口正确，返回空数据
    *   case20:user_id为空，user_id＝，其他参数正确，接口正确，返回空数据
    *   case21:user_id不存在：user_id=-1，其他参数正确，接口正确，返回空数据
    *   case22:pageSize不传，其他参数正确，接口正确，返回数据
    *   case23:pageSize=-1，其他参数正确，接口正确，返回数据
    *   case24:pageSize＝null，其他参数正确，pageSize默认35条，接口正确，返回数据
    *   case25:pageSize=，其他参数正确，默认35，接口正确，返回数据
    *   case26:pageSize=5，其他参数正确，接口正确，返回数据
    *   case27:parentCatId=0，user_id＝213002，其他参数正确，正常返回数据
    *   case28:parentCatId=27，childCatId=28，其他参数正确，正常返回数据
    *   case29:参数parentCatId不传，childCatId=28，其他参数正确，接口返回错误
    *   case30:parentCatId=27，childCatId不传，其他参数正确，接口返回错误
    *   case31:parentCatId为null：parentCatId=null，其他参数正确，接口返回错误
    *   case32:parentCatId为空，parentCatId=，其他参数正确，接口返回错误
    *   case33:parentCatId为不存在：parentCatId=1999999999，其他参数正确，接口返回空数据
    *   case34:childCatId为null：childCatId＝null，其他参数正确，接口返回错误
    *   case35:childCatId为空：childCatId＝，其他参数正确，接口返回错误
    *   case36:childCatId为不存在：childCatId＝19999999，其他参数正确，接口返回空数据
     */
    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void page_v2_HttpTest(final String caseId, final String description, final String shopIds, final String offset, final String pageSize, final String parentCatId, final String childCatId, final String sortBy, final String userId) {
        final String case_id = MemberTestBase.getCaseId(caseId);
        String res = null;
        String url = HttpPostUrlEnum.SDG_PAGEV2_URL.getUrl();
        if (case_id.equals("01")) { //case01：shopIds=3693，其他参数正确,接口正确，返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpSucess(res);
        } else if (case_id.equals("02")) {  //shopIds=3693,4499，其他参数正确,接口正确，返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpSucess(res);
        } else if (case_id.equals("03")) {  //必填参数shopIds不传，其他参数正确,接口返回错误
            url = url + "?offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.getHttpValue(res, "缺少参数");
        } else if (case_id.equals("04")) {  //shopIds为null：shopIds=null，其他参数正确,接口返回错误
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            //HttpUtils.checkHttpResCode(res,"0");
            HttpUtils.getHttpValue(res, "参数类型不匹配");
        } else if (case_id.equals("05")) {  //shopIds为空：shopIds=，其他参数正确,接口返回错误
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            //HttpUtils.checkHttpResCode(res,"0");
            HttpUtils.getHttpValue(res, "参数错误");
        } else if (case_id.equals("06")) {  //shopIds为不存在：shopIds=1999999999，其他参数正确,接口正确，返回空数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            //HttpUtils.checkHttpResCode(res,"0");
            HttpUtils.checkHttpStatus(res, true);
        } else if (case_id.equals("07")) {  //offset=1，其他参数正确,正常返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            //HttpUtils.checkHttpResCode(res,"0");
            HttpUtils.checkHttpSucess(res);
        } else if (case_id.equals("08")) {  //必填参数offset不传，其他参数正确,不传就默认1,正常返回数据
            url = url + "?shopIds=" + shopIds +"&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            //HttpUtils.checkHttpResCode(res,"0");
            HttpUtils.checkHttpStatus(res, true);
        } else if (case_id.equals("09")) {  //offset为offset=null，其他参数正确,默认1,正常返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, false);
        } else if (case_id.equals("10")) {  //offset为空，offset=，其他参数正确,默认1,正常返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);

        }else if (case_id.equals("11")) {  //offset=-1，其他参数正确，默认1，正常返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("12")) {  //sortBy=0，其他参数正确，接口正确，返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("13")) {  //sortBy=1，其他参数正确，接口正确，返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("14")) {  //sortBy=2，其他参数正确，接口正确，返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("15")) {  //sortBy=3，其他参数正确，接口正确，返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("16")) {  //sortBy不传，其他参数正确，不传默认为0，接口正确，返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("17")) {  //sortBy=4，其他参数正确，不等于0~3，默认为0，接口正确，返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("18")) {  //user_id不传，parentCatId=0，其他参数正确，接口正确，返回空数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("19")) {  //user_id为null，user_id=null，其他参数正确，接口正确，返回空数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, false);
        }else if (case_id.equals("20")) {  //user_id为空，user_id＝，其他参数正确，接口正确，返回空数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("21")) {  //user_id不存在：user_id=-1，其他参数正确，接口正确，返回空数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("22")) {  //pageSize不传，其他参数正确，接口正确，返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("23")) {  //pageSize=-1，其他参数正确，接口正确，返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("24")) {  //pageSize＝null，其他参数正确，pageSize默认35条，接口正确，返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, false);
        }else if (case_id.equals("25")) {  //pageSize=，其他参数正确，默认35，接口正确，返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("26")) {  //pageSize=5，其他参数正确，接口正确，返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("27")) {  //parentCatId=0，user_id＝213002，其他参数正确，正常返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("28")) {  //parentCatId=27，childCatId=28，其他参数正确，正常返回数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("29")) {  //参数parentCatId不传，childCatId=28，其他参数正确，接口返回错误
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, false);
        }else if (case_id.equals("30")) {  //parentCatId=27，childCatId不传，其他参数正确，接口返回错误
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("31")) {  //parentCatId为null：parentCatId=null，其他参数正确，接口返回错误
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, false);
        }else if (case_id.equals("32")) {  //parentCatId为空，parentCatId=，其他参数正确，接口返回错误
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, false);
        }else if (case_id.equals("33")) {  //parentCatId为不存在：parentCatId=1999999999，其他参数正确，接口返回空数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("34")) {  //childCatId为null：childCatId＝null，其他参数正确，接口返回错误
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, false);
        }else if (case_id.equals("35")) {  //childCatId为空：childCatId＝，其他参数正确，接口返回错误
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("36")) {  //childCatId为不存在：childCatId＝19999999，其他参数正确，接口返回空数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&childCatId=" + childCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }else if (case_id.equals("37")) {  //childCatId为不存在：childCatId＝19999999，其他参数正确，接口返回空数据
            url = url + "?shopIds=" + shopIds + "&offset=" + offset + "&pageSize=" + pageSize + "&parentCatId=" + parentCatId + "&sortBy=" + sortBy + "&userId=" + userId;
            System.out.println("url = " + url);
            res = httpClient.get(url);
            System.out.print("caseId:" + caseId + "---description = " + description + "\n" + "---result = " + res + "\n");
            HttpUtils.checkHttpStatus(res, true);
        }
    }
}
