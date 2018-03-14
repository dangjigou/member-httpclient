package com.member.controller.httpUrl.item.itemImport.ItemImportController;

import com.member.controller.common.MemberTestBase;
import com.member.test.utils.dataprovider.CsvDataProvider;
import com.member.test.utils.httputils.HttpPostUrlEnum;
import com.member.test.utils.httputils.HttpUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackson on 17/8/24.
 */
public class multipleUpdatePrice_HttpTest extends MemberTestBase {
    private Logger aLoger=Logger.getLogger(multipleUpdatePrice_HttpTest.class);
    Map<String,String> uploadImagesParams = new HashMap<String, String>();


    /**
     * 脚本说明：
     * case01: 正常用例，上传图片成功
     **/

    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class)
    public void multipleUpdatePrice_HttpTest(String caseId ,String description ,String fileName,String filePath) throws IOException {
        String res = null;
//        HttpClient httpClient = new HttpClient();
        final String case_id = MemberTestBase.getCaseId(caseId);
        String url = HttpPostUrlEnum.RETAIL_MULTIPLE_UPDATE_PRICE.getUrl();
        System.out.print("url = " + url + "\n\r");
        try{
            if(case_id.equals("01")){
                butlerLgoinGray("longying2000","lzh0605");

//                filePath = System.getProperty("user.dir")  + "/src/test/resources/testres/UploadImages_Http/" + fileName;
//                filePath = MultipleUpdatePrice_HttpTest.class.getClassLoader().getResource(filePath).getPath();
                filePath = new File(this.getClass().getResource("").getFile(),"../../../../../../../../../../src/test/resources/"+filePath).getCanonicalPath();
                System.out.println("---------------------"+filePath);
                System.out.println("filePath = " +filePath);
//                for(int i =0;i<500;i++){
//                    HttpClient httpClient = new HttpClient();
//                    res = httpClient.getHttpResponseMultipartFormDataByPost(url, fileName, filePath);
//                    System.out.print("caseId:" + caseId + ":result = " + res + "\n");
//                    Thread.sleep(1000);
//                }
                res = httpClient.getHttpResponseMultipartFormDataByPost(url, fileName, filePath);
                System.out.print("caseId:" + caseId + ":result = " + res + "\n");
                HttpUtils.checkHttpSucess(res);
            }
        } catch (Exception e) {
            System.err.println("脚本出现未知异常！" + e);
        } finally {
//            //账号登出
            logOutAll();
        }
    }

    private void init(String fileName,String filePath){
        //组装上传图片入參
        uploadImagesParams.put("fileName", fileName);
        uploadImagesParams.put("filePath", filePath);
    }
}