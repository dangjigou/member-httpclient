package com.member.test.utils.database;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.member.test.utils.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by jackson on 16/7/7.
 */
public class DBUtils {
    private static ThreadLocal<Integer> argsIndex = new ThreadLocal();

    private static ThreadLocal<Connection> lockDBConnectionHolder = new ThreadLocal();

    protected static Logger logger = LoggerFactory.getLogger(DBUtils.class);

    public static DataMap getQueryResultMap(String sql,String DBName) {

        DataMap map = null;
        DBConn conn = new DBConn();
        ResultSet resultSet = conn.executeQuery(sql,DBName);
        try {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            if (resultSet.next()) {
                map = new DataMap();
                for (int i = 1; i <= rsmd.getColumnCount(); i++)
                    map.put(StringUtils.toUpperCase(rsmd.getColumnName(i)), resultSet.getObject(i));
            }
        }
        catch (SQLException e) {
            if (logger.isErrorEnabled())
                logger.error("数据库操作出错。", e);
        } finally {
            conn.close();
        }

        return map;
    }

    public static String getStringValue(String sql,String DBname)
    {
        String value = null;

        DBConn conn = new DBConn();
        ResultSet resultSet = conn.executeQuery(sql);
        try {
            if (!resultSet.next())
                return null;
            value = resultSet.getString(1);
        } catch (SQLException e) {
           /* if (logger.isErrorEnabled())
                logger.error("数据库操作出错。", e);*/
        } finally {
            conn.close();
        }

        return value;
    }

    public static int getUpdateResultMap(String sql,String DBname)
    {
        DBConn conn = new DBConn();
        int resultSet = 0;
        try {
            resultSet = conn.executeUpdate(sql,DBname);
        } catch (Exception e) {
            /*if (logger.isErrorEnabled())
                logger.error("数据库操作出错。", e);*/
            return -1;
        } finally {
            conn.close();
        }

        return resultSet;
    }
}
