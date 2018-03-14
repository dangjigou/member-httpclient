package com.member.test.utils.restult;

/**
 * Created by luzhenhui on 15/8/18.
 */
public enum HttpResultCode {

    GENERAL_SUCCESS(                0,  "操作成功"),
    LOGIN_SUCCESS(                  0,  "登录成功"),
    LOGOUT_SUCCESS(                 0,  "退出登录成功"),
    REGISTER_SUCCESS(               0,  "注册成功"),
    PWD_RESET_SUCCESS(              0,  "密码重置成功"),
    USER_SAVE_SUCCESS(        		0,   	"用户信息保存成功"),
    GENERAL_FAIL(                   10201,  "操作失败"),
    PARAMETER_ERROR(                10202,  "参数校验错误"),
    LOGIN_EXCEPTION(                10203,  "登录异常，请稍后重试"),
    CODE_ERROR(                     10204,  "验证码有误，请重新输入"),
    MOBILE_CODE_ERROR(              10205,  "手机号码或验证码有误"),
    PASSWORD_ERROR(                 10206,  "密码错误，请重新输入"),
    MOBILE_ERROR(                   10207,  "手机号码错误，请重新输入"),
    MOBILE_PASSWORD_ERROR(          10208,  "手机或密码错误"),
    CODE_PASSWORD_ERROR(            10209,  "请输入密码或验证码"),
    USER_IN_BLACKLIST_ERROR(        10210,  "您的账号存在异常下单，已被限制登录"),
    USER_NO_PASSWORD_ERROR(         10211,  "您的账号未设置密码，请用验证码登录并设置密码"),
    USER_NOT_LOGIN_ERROR(           10212,  "您还没有登录"),
    USER_OR_PASSWORD_ERROR(         10213,  "用户名或密码错误，请重新输入"),
    PASSWORD_NOT_SAME_ERROR(        10214,  "两次输入密码不一致"),
    USER_ALREADY_EXIST_ERROR(       10215,  "用户已注册，请登录"),
    SYSTEM_EXCEPTION(               10216,  "会员服务异常，请稍后重试"),
    USER_NOT_EXIST_ERROR(           10217,  "该用户未注册过，请先去注册"),
    USER_SAVE_ERROR(         		10218,  "用户信息保存失败"),
    NOT_EXIST(         				10219,  "记录不存在"),
    ROLE_ERROR(               		10220,  "角色异常"),
    PERMISSION_TOO_LOWER(           10221,  "您的权限不够，不能登录"),
    PERMISSION_NOT_AllOW_SELLER(    10222,  "卖家权限的用户才能登录闪电帮"),
    NEED_BINDING_MOBILE(            10223,  "需要绑定手机号"),
    ALREADY_BINDING_MOBILE_ERROR(   10224,  "该微信已经绑定手机号"),
    ALREADY_BINDING_WX_ERROR(   	10225,  "该手机号已经绑定微信"),
    WB_ALREADY_BINDING_MOBILE_ERROR(   10256,  "该微博已经绑定手机号"),
    TB_ALREADY_BINDING_MOBILE_ERROR(   10257,  "该淘宝账号已经绑定手机号"),
    ALREADY_BINDING_TB_ERROR(   	10258,  "该手机号已经绑定淘宝账号"),
    ALREADY_BINDING_WB_ERROR(   	10259,  "该手机号已经绑定淘宝账号"),
    
    USER_SHOP_NOT_MATCH(    		10228,  "用户和店铺不匹配"),
    PASSWORD_FAILURE_TOO_MUCH(      10227,  "密码错误次数过多，请半小时后重试"),
    LOGIN_LOCK_FAILURE(             10229,  "密码错误次数过多，登录已被锁定，请重置密码或明天再试"),
    

    ADDRESS_SAVE_SUCCESS(         	0,     "收货地址保存成功"),
    ADDRESS_SAVE_ERROR(         	10230, "收货地址保存失败"),   
    ADDRESS_DELETE_SUCCESS(         0,     "收货地址删除成功"),
    ADDRESS_DELETE_ERROR(         	10231, "收货地址删除失败"),
    CHECK_SUCCESS(                	0,     "OK"),
    ADDRESSID_NOT_NULL(         	10232, "收货地址ID错误"),
    USERID_NOT_NULL(				10234, "用户ID错误"),
    RECEIVERNAME_NOT_NULL(			10235, "收货人错误"),
    CITY_NOT_NULL(					10236, "城市错误"),
    ADDRESS_NOT_NULL(				10237, "所在地区错误"),
    ROLE_NOT_NULL(					10238, "角色错误"),
    ADDRESS_NOT_EXIST(				10239, "收货地址不存在"),
    ADDRESSID_ERROR(			    10240, "收货地址ID异常"),
    PARAM_NOT_NULL(			    	10241, "输入参数错误"),
    USER_NOT_SET_DEFAULT_ADDRESS(	10242, "您没有设置默认地址"),
    ADDRESS_COUNT_LARGER_TEN(		10243, "地址个数超过10个了，请先删除部分地址"),
    ADDRESS_EXIST(					10244, "您添加的地址已存在"),
    LANDMARKID_NOT_NULL(			10245, "定位ID错误"),
    PASSWORD_NULL_ERROR(            10246, "请输入密码"),
    PASSWORD_LENGTH_ERROR(          10247, "密码长度必须是6-16位"),
    PASSWORD_TOO_EASY(              10248, "密码太简单了"),
    COMMENT_ERROR(         			10249, "内容异常"),
    CITY_NOT_SAME_ERROR(         	10250, "城市不一致"),
    AID_ERROR(                  	10251, "用户名错误"),
    CONTACT_INFO_TOO_LONG(          10252, "联系方式过长"),
    
    BUYER_USER_NOT_EXIST_ERROR( 	10265,  "该用户未注册过,请用验证码登录"),

    
    USER_BLACK_SAVE_SUCCESS(        0,  "黑名单用户保存成功"),
    USER_BLACK_SAVE_ERROR(         	10250, "黑名单用户保存失败"),
    USER_BLACK_DELETE_SUCCESS(      0,  "黑名单用户删除成功"),
    USER_BLACK_DELETE_ERROR(        10251, "黑名单用户删除失败"),
    USER_IS_BLACK(        			10252, "该用户是黑名单用户"),
    USER_IS_NOT_BLACK(        		10253, "该用户不是黑名单用户"),
    
    PERMISSION_LOWER(        		10260, "权限太低，无法操作"),
    NOT_INNER_USER(        			10261, "该用户非有效内部用户，不能升级权限"),
    NO_PERMISSION(					10262, "无权操作"),
    MOBILE_HAS_REGISTER(			10263, "该手机号已经注册"),

    END(10100, "");

    private int code;

    private String message;

    HttpResultCode(int code, String message) {
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
        return (code == GENERAL_SUCCESS.getCode());
    }

}
