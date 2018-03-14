package com.member.controller.dubbo.ItemListApiService;

import com.alibaba.fastjson.JSON;
import com.member.test.utils.StringUtils;
import com.shandiangou.itemcenter.open.domain.BaseItemVO;
import org.junit.Assert;
import org.testng.annotations.Test;
import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jackson on 16/10/13.
 */
public class GetMyFrequentBuy_DubboTest extends MemberTestBase {
    /**
     * 脚本说明：
     * case01:
     **/

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class)
    public void getMyFrequentBuy_DubboTest(final String caseId, final String description, String status, String resultCode,
                                           Long userId,String shopIds1) {

        final String case_id = MemberTestBase.getCaseId(caseId);
        Set<Long> shopIds = StringUtils.convertStringToLongSet(shopIds1);

        if(case_id.equals("02")){
            try {

                //登录
//                logIn("buyer", "12345679000", "1130");

                List<BaseItemVO> list =  itemListApiService.getMyFrequentBuy(userId,shopIds);
                System.out.println("list = " + JSON.toJSONString(list));
                Assert.assertTrue(list.size()==0);

            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
        }

        else if(case_id.equals("03")){
            try {

                //登录
//                logIn("buyer", "12345679000", "1130");

                List<BaseItemVO> list =  itemListApiService.getMyFrequentBuy(userId,shopIds);
                System.out.println("list = " + JSON.toJSONString(list));
                Assert.assertTrue(list.size()==0);

            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
        }

        else {
            try {

                //登录
//                logIn("buyer", "12345679000", "1130");

                List<BaseItemVO> list = itemListApiService.getMyFrequentBuy(userId, shopIds);
                System.out.println("list = " + JSON.toJSONString(list));
                Assert.assertTrue(list.size() > 0);

            } catch (Exception e) {
                System.err.println("脚本出现未知异常！" + e);
            } finally {
                //账号登出
                logOutAll();
            }
        }
    }
}