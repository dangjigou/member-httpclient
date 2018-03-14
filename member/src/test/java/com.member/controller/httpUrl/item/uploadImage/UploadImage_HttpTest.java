//package com.member.controller.httpUrl.item.uploadImage;
//
//import com.member.controller.common.MemberTestBase;
//import com.member.test.utils.dataprovider.CsvDataProvider;
//import com.member.test.utils.httputils.HttpPostUrlEnum;
//import com.member.test.utils.httputils.HttpUtils;
//import org.apache.log4j.Logger;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by jackson on 16/8/29.
// */
//public class UploadImage_HttpTest extends MemberTestBase {
//    private Logger aLoger=Logger.getLogger(UploadImage_HttpTest.class);
//    Map<String,String> uploadImagesParams = new HashMap<String, String>();
//
//    /**
//     * 脚本说明：
//     * case01: 正常用例，上传图片成功
//     **/
//
//    @BeforeTest
//    public void setUp(){
//        logInForCrm("crm","13456790220","52shandiangou");
//    }
//
//    @Test(dataProvider = "CsvDataProvider",dataProviderClass = CsvDataProvider.class,threadPoolSize = 2, invocationCount = 50,  timeOut = 10000)
//    public void uploadImages_HttpTest(String caseId ,String description ,String fileName,String filePath) throws IOException {
//        String res = null;
//        final String case_id = MemberTestBase.getCaseId(caseId);
//        String url = HttpPostUrlEnum.SDG_UPLOADIMAGES_URL.getUrl();
//        System.out.print("url = " + url + "\n\r");
////            filePath = System.getProperty("user.dir")  + "/src/test/resources/testres/UploadImages_Http/" + fileName;
////            filePath = UploadImages_HttpTest.class.getClassLoader().getResource(filePath).getPath();
//        filePath = new File(this.getClass().getResource("").getFile(),"../../../../../../../../src/test/resources/"+filePath).getCanonicalPath();
//        System.out.println("filePath = " +filePath);
//        res = httpClient.getHttpResponseMultipartFormDataByPost(url, fileName, filePath);
//        System.out.print("caseId:" + caseId + ":result = " + res + "\n");
//        HttpUtils.checkHttpSucess(res);
//    }
//
//    private void init(String fileName,String filePath){
//        //组装上传图片入參
//        uploadImagesParams.put("fileName", fileName);
//        uploadImagesParams.put("filePath", filePath);
//    }
//}
//}