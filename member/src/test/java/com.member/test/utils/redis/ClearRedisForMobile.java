package com.member.test.utils.redis;

import com.member.test.utils.restult.Result;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jackson on 16/9/2.
 */
//清除用户缓存(发送验证码次数、验证码校验次数、密码校验次数)
public class ClearRedisForMobile {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final String LAST_SEND_TIME_KEY = "last_send_time";
    private static final String activity = "verifyAuthCode";
    private static final String activityDay = "verifyAuthCode_day";
    private static final String VERIFY_PASSWORD_ACTIVITY = "verifyMobilePassword";
    private static final String VERIFY_PASSWORD_ACTIVITY_DAY = "verifyMobilePassword_day";
    static RedisUtils redisUtils = new RedisUtils();

    public static String clearRedisForMobile(String mobile){
        String sendCountKey = mobile + dateFormat.format(new Date());
        String lastSendTimeKey = mobile + LAST_SEND_TIME_KEY;
        String userCodeActivityKey = mobile + "-" + activity;
        String userCodeActivityKeyDay = mobile + "-" + activityDay;
        String userPasswordActivityKey = mobile + "-" + VERIFY_PASSWORD_ACTIVITY;
        String userPasswordActivityKeyDay = mobile + "-" + VERIFY_PASSWORD_ACTIVITY_DAY;

        //清空发送验证码次数和时间
        redisUtils.redisDel(sendCountKey,0);
        redisUtils.redisDel(lastSendTimeKey,0);

        //清空密码校验错误次数
        redisUtils.redisDel(userCodeActivityKey,0);
        redisUtils.redisDel(userCodeActivityKeyDay,0);

        //清空验证码校验错误次数
        redisUtils.redisDel(userPasswordActivityKey,0);
        redisUtils.redisDel(userPasswordActivityKeyDay,0);

        return Result.Success("清空成功");
    }
}
