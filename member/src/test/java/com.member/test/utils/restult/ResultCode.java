package com.member.test.utils.restult;

/**
 * Created by luzhenhui on 15/8/24.
 */
public enum ResultCode {

    SUCCESS(				0, "操作成功"),
    SEND_SMS_SUCCESS(		0, "发送成功"),
    SEND_VOICE_SUCCESS(		0, "已发送"),
    VERIFY_CODE_SUCCESS(	0, "已发送"),
    SEND_TOO_FAST(			10101, "您手太快咯，请慢点~"),
    SEND_SMS_UPPER_LIMIT(	10102, "您今天的发送量已达上限，请明后天再来"),
    SEND_VOICE_UPPER_LIMIT(	10108, "您今天的语音验证码发送量已达上限，请明后天再来"),
    MOBILE_ERROR(			10103, "手机号码有误"),
    USER_BLACKLIST(			10104, "您的账号存在异常，如有异议，请联系闪电购客服咨询，4001575151"),
    CODE_ERROR(				10105, "验证码错误"),
    SEND_SMS_FAIL_RETRY(	10106, "短信发送失败，请重试"),
    SEND_VOICE_FAIL_RETRY(	10107, "语音发送失败，请重试"),
    
    USER_ID_ERROR(			10111, "用户参数非法"),
    SHOP_ID_ERROR(			10112, "店铺参数非法"),
    USER_NOT_SELLER(		10113, "用户不是店主"),
    SELLER_CITY_NOT_SHOP_CITY(		10114, "自营批发店城市和店主店铺不在同一个城市"),
    ROW_NOT_EXISTS(			10115, "记录不存在"),
    SELLER_TO_ZY_SHOP(			10115, "自营店店主不能加入自己自营店白名单"),
    
    USER_EXISTS(			10120, "用户已存在"),
    USER_ADD_FAIL(			10121, "用户新增失败"),
    
    SYSTEM_EXCEPRION(		10110, "服务器被玩坏了!!!"),

    END(100, "");

    private int code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {

        return code == SUCCESS.getCode();
    }

}
