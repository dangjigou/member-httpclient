package com.member.test.utils.httputils;

/**
 * Created by jackson on 15/12/17.
 */
public class HttpResult {

    //返回结果状态
    private  static  final  String[] RESULT_STATUS = {"status","success"};

    //返回结果码
    private  static  final  String RESULT_CODE = "responseCode";

    //返回结果实体
    private static  final  String[] RESULT_BODY = {"entry","result"};

    //返回结果true/false
    private boolean status;

    //返回错误码
    private String responseCode;

    //返回结果信息
    private String message;

    //返回结果实体
    private String entry;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEntry() {return entry;}

    public void setEntry(String entry) {
        this.entry = entry;
    }


}
