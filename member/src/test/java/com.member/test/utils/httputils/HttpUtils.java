package com.member.test.utils.httputils;

import com.member.test.utils.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * http校验工具类
 * Created by jackson on 15/12/8.
 */
public class HttpUtils {

    //返回结果状态
    private  static  final  String[] RESULT_STATUS = {"status","success"};

    //返回结果码
    private  static  final  String RESULT_CODE = "responseCode";

    //返回结果实体
    private static  final  String[] RESULT_BODY = {"entry","result"};

    private static final String RESULT_MESSAGE = "message";


    /**
     * 校验http返回status结果是否成功
     * @param result http返回结果
     */
    public static  String checkHttpSucess(String result) {
        String message = null;
        String statusResult = null;

        try{
            Map<String,String> resultMap = parseJson2Map(result);
            for (String tmpStr:RESULT_STATUS) {
                if (StringUtils.isBlank(resultMap.get(tmpStr))) {
                    continue;
                }
                statusResult = resultMap.get(tmpStr);
                break;
            }
            message = resultMap.get("message");
            Assert.assertTrue(statusResult.equals("true"),"http返回状态失败！");

        } catch (Exception e){
            e.getStackTrace();
        }finally {
            if (StringUtils.isNotBlank(message)){
                System.out.println("返回结果信息：" + message);
            }
        }

        return statusResult;

    }

    /**
     * 校验http返回status结果是否符合预期
     * @param result http返回结果
     * @param status 预期status结果
     */
    public static  String checkHttpStatus(String result,boolean status) {
        String message = null;
        String statusResult = null;
        try{
            Map<String,String> resultMap = parseJson2Map(result);
            for (String tmpStr:RESULT_STATUS) {
                if (StringUtils.isBlank(resultMap.get(tmpStr))) {
                    continue;
                }
                statusResult = resultMap.get(tmpStr);
                break;
            }
            message = resultMap.get("message");

            Assert.assertEquals(statusResult, String.valueOf(status), "http返回状态不符合预期！");

        } catch (Exception e){
            e.getStackTrace();
        }finally {
            if (StringUtils.isNotBlank(message)){
                System.out.println("返回结果信息：" + message);
            }
        }

        return statusResult;

    }

    /**
     *校验http返回的结果码是否符合预期
     * @param result 返回结果
     * @param exceptCode 预期结果码
     *
     */
    public  static  void checkHttpResCode(String result,String exceptCode){
        String message = null;
        try{
            Map<String,String> resultMap = parseJson2Map(result);
            String statusResult =  resultMap.get(RESULT_CODE);

            Assert.assertTrue(!statusResult.isEmpty() && statusResult.equals(exceptCode),
                    "http返回结果码错误！预期结果码：" + exceptCode + "，实际返回结果码：" + statusResult);
            message = resultMap.get("message");
        }catch (Exception e){
            e.getStackTrace();
        }finally {
            if (StringUtils.isNotBlank(message)){
                System.out.println("返回结果信息：" + message);
            }
        }

    }

    /**
     *校验http返回的结果码是否符合预期
     * @param result 返回结果
     * @param exceptMessage 预期message
     *
     */
    public  static  void checkHttpResMessage(String result,String exceptMessage){
        String message = null;
        try{
            Map<String,String> resultMap = parseJson2Map(result);
            String statusResult =  resultMap.get(RESULT_MESSAGE);

            Assert.assertTrue(!statusResult.isEmpty() && statusResult.equals(exceptMessage),
                    "http返回message错误！预期message：" + exceptMessage + "，实际返回message：" + statusResult);
            message = resultMap.get("message");
        }catch (Exception e){
            e.getStackTrace();
        }finally {
            if (StringUtils.isNotBlank(message)){
                System.out.println("返回结果信息：" + message);
            }
        }

    }

    /**
     *返回http结果码
     * @param result 返回结果
     *
     */
    public static String  getHttpResCode(String result){
        String statusResult  = null;
        String message = null;
        try{
            Map<String,String> resultMap = parseJson2Map(result);
            statusResult =  resultMap.get(RESULT_CODE);
            message = resultMap.get("message");
        }catch (Exception e){
            e.getStackTrace();
        }finally {
            if (StringUtils.isNotBlank(message)){
                System.out.println("返回结果信息：" + message);
            }
        }

        return statusResult;


    }



    /**
     * 校验http返回实体内值的正确性
     * @param result 校验目标
     * @param exceptMap 预期结果集合
     */
    public static void checkHttpEntry(String result,Map<String,Map> exceptMap){
        JSONObject  resultEntryJson = new JSONObject();
        String message = null;
        try{
            String resultEntryStr = null;
            Map<String,String> resultMap = parseJson2Map(result);
            //从返回结果中取出实体
            for (String tmpStr:RESULT_BODY) {
                if (resultMap.get(tmpStr).isEmpty()) {
                    continue;
                }
                resultEntryStr = resultMap.get(tmpStr);
            }

            JSONArray array = new JSONArray(resultEntryStr);
            Set exceptEntrySet = exceptMap.entrySet();

            //取出json数组的每个json
            for (int i = 0; i < array.length(); i++) {
                JSONObject iObj = array.getJSONObject(i);
                //遍历预期结果的key与json做对比
                for (Object entrySet:exceptEntrySet){

                };
            }
            message = resultMap.get("message");

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (StringUtils.isNotBlank(message)){
                System.out.println("返回结果信息：" + message);
            }
        }
    }

    /**
     *校验http返回的实体内值不为空
     * @param result 返回结果
     *
     */
    public  static  void checkHttpEntryNotEmpty(String result){
        String resultEntryStr = null;
        try{
            Map<String,String> resultMap = parseJson2Map(result);
            for (String tmpStr:RESULT_BODY) {
                if (StringUtils.isBlank(resultMap.get(tmpStr))) {
                    continue;
                }
                resultEntryStr = resultMap.get(tmpStr);
                break;
            }
            Assert.assertNotEquals(resultEntryStr,"[]");
            System.out.println("返回的实体内值：" + resultEntryStr);
        }catch (Exception e){
            e.getStackTrace();
        }

    }

    /**
     * 根据key从结果实体（entry或result）中查询期望的值
     * 如果返回的实体为JsonArray，只查询第一个
     * @param result http返回结果
     * @param exceptValue 期望查询的key
     */
    public static String getHttpValue(String result,String exceptValue){
        String auctalResult = null;
        String message = null;
        try{
            Map<String,String> resultMap = parseJson2Map(result);

            for (String tmpStr:RESULT_BODY) {
                if (StringUtils.isBlank(resultMap.get(tmpStr))) {
                    continue;
                }
                String entry = resultMap.get(tmpStr);
                if (entry.startsWith("{")){
                    auctalResult = new JSONObject(entry).get(exceptValue).toString();
                }else{
                    JSONObject firResult = (JSONObject)new JSONArray(entry).get(0);
                    auctalResult = firResult.get(exceptValue).toString();
                }

            }
            message = resultMap.get("message");

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (StringUtils.isNotBlank(message)){
                System.out.println("返回结果信息：" + message);
            }
        }
        return  auctalResult;


    }

    /**
     * 将Json字符串转换为Map
     * @param jsonStr
     * @return
     */
    private static Map<String,String> parseJson2Map(String jsonStr){
        Map<String, String> map = new HashMap<String, String>();
        //最外层解析
        JSONObject json = new JSONObject(jsonStr);
        Iterator iterator =  json.keys();
        while (iterator.hasNext()){
            String key = String.valueOf(iterator.next());
            String value =  json.get(key).toString();
            map.put(key, value);
        }
        return map;

    }



}
