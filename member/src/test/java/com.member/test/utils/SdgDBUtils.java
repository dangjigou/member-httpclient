package com.member.test.utils;

import com.member.test.utils.database.DBUtils;
import com.member.test.utils.database.DataMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * 数据库操作
 * Created by Jackson on 16/7/7.
 */
public class SdgDBUtils {

    /** 数据库连接 */
    private static Connection        sdgDBConnection        = null;

    /** 预查询 */
    private static PreparedStatement sdgDBPreparedStatement = null;


    /** 用于传入自定义参数的当前位置记录，使用到自定义不确定参数时必需初始化为0 */
    private static int               argsIndex              = 0;

    /** 标志锁状态 */
    private static boolean           lockFlag               = false;

    /**
     * 按条件的交集查询count
     *
     * @param tableName 数据表名称
     * @param conditions 查询条件
     * @return 记录数量
     */
    public static int selectCountDB(String tableName, String[] conditions,String DBname) {

        if (null == conditions) {
            System.out.println("查询 " + tableName + " 数据记录，参数错误");
            return -1;
        }

        String tempConditonString = "";
        String selectSql = "SELECT count(*) n from " + tableName;
        if (0 != conditions.length) {
            tempConditonString = genConditionSql(conditions);
            selectSql = selectSql + " WHERE " + tempConditonString;
            System.out.println(selectSql);
        }
        DataMap dataMap = DBUtils.getQueryResultMap(selectSql,DBname);

        int count = dataMap.getIntValue("n");

        return count;
    }


    /**
     * 按条件的交集查询count
     *
     * @param tableName 数据表名称
     * @param condition 查询条件
     * @return 数据记录
     */
    public static int selectCountDB(String tableName, String condition,String DBname) {
        String[] conditions = { condition };
        return selectCountDB(tableName, conditions,DBname);
    }

    /**
     * 按单一条件查询表的某一属性值
     *
     * @param tableName 数据表名称
     * @param key 查询字段
     * @param condition 查询条件
     * @return 字段内容
     */
    public static String selectStrDB(String tableName, String key, String condition,String DBname) {

        if (StringUtils.isBlank(condition)) {
            System.out.println("查询 " + tableName + " 数据记录，条件为空");
            return null;
        }

        String selectSql = "SELECT " + key + " from " + tableName + " WHERE " + condition;

        return DBUtils.getStringValue(selectSql,DBname);
    }


    /**
     * 按条件的交集查询表的某一属性值
     *
     * @param tableName 数据表名称
     * @param key 查询字段
     * @param conditions 查询条件
     * @param subCondition 查询子条件
     * @return 字段内容
     */
    public static String selectStrDB(String tableName, String key, String[] conditions,
                                     String subCondition,String DBname) {

        if (null == conditions || 0 == conditions.length) {
            System.out.println("查询 " + tableName + " 数据记录，参数错误");
            return null;
        }

        if (StringUtils.isBlank(subCondition)) {
            subCondition = "";
        }

        String tempConditonString = genConditionSql(conditions);

        String selectSql = "SELECT " + key + " from " + tableName + " WHERE " + tempConditonString
                + subCondition;

        return DBUtils.getStringValue(selectSql,DBname);
    }


    /**
     * 按单一条件查询记录
     *
     * @param tableName 数据表名称
     * @param condition 查询条件
     * @param subCondition 查询子条件
     * @return 数据记录
     */
    public static DataMap selectMapDB(String tableName, String condition, String subCondition,String DBname) {
        String[] conditions = { condition };
        return selectMapDB(tableName, conditions, subCondition,DBname);
    }

    /**
     * 按条件的交集查询记录
     *
     * @param tableName 数据表名称
     * @param conditions 查询条件
     * @param subCondition 查询子条件
     * @return 数据记录
     */
    public static DataMap selectMapDB(String tableName, String[] conditions, String subCondition,String DBname) {

        if (null == conditions || 0 == conditions.length) {
            System.out.println("查询 " + tableName + " 数据记录，参数错误");
            return null;
        }

        if (StringUtils.isBlank(subCondition)) {
            subCondition = "";
        }

        String tempConditonString = genConditionSql(conditions);

        String selectSql = "SELECT * from " + tableName + " WHERE " + tempConditonString
                + subCondition;

        DataMap map = DBUtils.getQueryResultMap(selectSql,DBname);

        return map;

    }


    /**
     * 按单个条件修改记录
     *
     * @param tableName
     * @param col
     * @param val
     * @param condition
     * @author jackson
     */
    public static void upDateDB(String tableName, String col, String val, String condition, String DBname) {

        String[] cols = { col };
        String[] vals = { val };
        String[] conditions = { condition };
        upDateDB(tableName, cols, vals, conditions,DBname);

    }


    /**
     * 按条件的交集修改记录
     *
     * @param tableName
     * @param cols
     * @param values
     * @param conditions
     * @author jackson
     */
    public static void upDateDB(String tableName, String[] cols, String[] values,
                                String[] conditions,String DBname) {

        if (cols.length != values.length || null == conditions || 0 == conditions.length) {
            System.out.println("修改 " + tableName + " 数据记录，参数错误");
            return;
        }

        String tempSql = genColValSql(cols, values);

        String tempConditonString = genConditionSql(conditions);

        String upDateSql = "UPDATE " + tableName + " SET " + tempSql + " WHERE "
                + tempConditonString;

        System.out.print("upDataSql = " + upDateSql);
        upDateSql = upDateSql.replaceAll("'null'", "null");

        DBUtils.getUpdateResultMap(upDateSql,DBname);

    }

    /**
     * 按单个条件删除记录
     *
     * @param tableName
     * @param condition
     * @author jackson
     */
    public static void deleteDB(String tableName, String condition,String DBname) {

        String[] conditions = { condition };
        deleteDB(tableName, conditions,DBname);

    }

    /**
     * 按条件的交集删除记录
     *
     * @param tableName
     * @param conditions
     * @author jackson
     */
    public static void deleteDB(String tableName, String[] conditions,String DBname) {

        if (null == conditions || 0 == conditions.length) {
            System.out.println("删除 " + tableName + " 数据记录，参数错误");
            return;
        }

        String tempConditonString = genConditionSql(conditions);

        String deleteSql = "DELETE FROM " + tableName + " WHERE " + tempConditonString;
        System.out.print("deleteSql = " + deleteSql);

        DBUtils.getUpdateResultMap(deleteSql,DBname);

    }

    /**
     * 多个表使用同一条件定位（适用于分表）
     *
     * @param tableName
     * @param conditions
     */
    public static void deleteDB(String[] tableName, String conditions,String DBname) {

        if (null == tableName || 0 == tableName.length) {
            System.out.println("删除 " + tableName + " 数据记录，参数错误");
            return;
        }

        for (String table : tableName) {
            deleteDB(table, conditions,DBname);
        }
    }


    /**
     * 根据传入的keys、vals插入记录
     *
     * @param tableName
     * @param keys
     * @param vals
     * @author jackson
     */
    public static void insertDB(String tableName, String keys, String vals,String DBname) {

        keys = "(" + keys + ")";
        vals = "(" + vals + ")";

        String insertSql = "INSERT INTO " + tableName + keys + " VALUES " + vals;

        DBUtils.getUpdateResultMap(insertSql,DBname);
    }

    /**
     * 组装条件
     *
     * @param conditions
     * @return
     * @author jackson
     */
    public static String genConditionSql(String[] conditions) {

        StringBuilder tempConditon = new StringBuilder();

        for (int i = 0; i < conditions.length; i++) {
            tempConditon.append(" AND ").append(conditions[i]);
        }

        return tempConditon.toString().replaceFirst(" AND ", "");

    }

    /**
     * 组装属性&值
     *
     * @param cols
     * @param values
     * @return
     * @author jackson
     */
    public static String genColValSql(String[] cols, String[] values) {

        StringBuilder tempSql = new StringBuilder();

        for (int i = 0; i < cols.length; i++) {
            tempSql.append(",").append(cols[i]).append(" = '").append(values[i]).append("'");
        }

        return tempSql.toString().replaceFirst(",", "");

    }

}
