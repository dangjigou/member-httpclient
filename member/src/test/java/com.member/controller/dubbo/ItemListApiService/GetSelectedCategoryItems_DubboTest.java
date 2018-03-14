package com.member.controller.dubbo.ItemListApiService;

import com.alibaba.fastjson.JSON;
import com.member.test.utils.StringUtils;
import com.shandiangou.itemcenter.open.domain.item.SelectedCategoryVO;
import org.junit.Assert;
import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jackson on 16/10/11.
 */
public class GetSelectedCategoryItems_DubboTest extends MemberTestBase {
    /**
     * 脚本说明：
     * case01:
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void getSelectedCategoryItems_DubboTest(final String caseId, final String description, String status, String resultCode,
                                                  String shopIds1,String withFixedFrontCatIds1,int minItemNumPerCat) {
        String res = null;
        List<Long> shopIds = StringUtils.convertStringToLongList(shopIds1);
        List<Integer> withFixedFrontCatIds = StringUtils.convertStringToIntergerList(withFixedFrontCatIds1);

            try {

                //登录
//                logIn("buyer", "12345679000", "1130");
                SelectedCategoryVO selectedCategoryVO = itemListApiService.getSelectedCategoryItems(shopIds,withFixedFrontCatIds,minItemNumPerCat);
                System.out.println("selectedCategoryVO = " + JSON.toJSONString(selectedCategoryVO));
                Assert.assertTrue(selectedCategoryVO.getTop2SecondFrontCatItems().size()>0);

            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
    }
}