package com.member.controller.dubbo.ItemListApiService;

import com.alibaba.fastjson.JSON;
import com.member.test.utils.StringUtils;
import com.member.test.utils.httputils.HttpUtils;
import com.shandiangou.itemcenter.open.domain.ItemVO;
import com.shandiangou.itemcenter.open.domain.Page;
import org.junit.Assert;
import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jackson on 16/10/14.
 */
public class GetFineQualityItems_DubboTest extends MemberTestBase {
    /**
     * 脚本说明：
     * case01:
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void getFineQualityItems_DubboTest(final String caseId, final String description, String status, String resultCode,
                                             String shopIds1,int offset,int count) {

//        List<Long> shopIds =  Arrays.asList(3693l,4670l,4688l,4697l,5455l,5471l,1000398l,1001785l,1002306l,1002482l,4499l);

        List<Long> shopIds = StringUtils.convertStringToLongList(shopIds1);

            try {

                //登录
//                logIn("buyer", "12345679000", "1130");

                Page<ItemVO> itemVOPage = itemListApiService.getFineQualityItems(shopIds,offset,count);
                System.out.println("itemVOPage = " + JSON.toJSONString(itemVOPage));
                Assert.assertTrue(itemVOPage.getResult().size()>0);

            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
    }
}