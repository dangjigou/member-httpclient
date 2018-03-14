package com.member.test.utils.database;


import java.sql.*;

/**
 * 获取mysql DB连接；
 * Created by Jackson on 16/7/7.
 */

public class DBConn {

    private Statement statement = null;
    private Connection conn=null;

    private ResultSet myResultSet = null;

    /**
     * 查询sql执行
     * @param sql
     * @return
     */
    public ResultSet executeQuery(String sql)
    {
        try
        {
            initConnection();

            this.myResultSet = statement.executeQuery(sql);

        } catch (Exception ex) {
            //logger.error("数据库操作出错。sql=[" + sql + "], tableName=[" + tableName + "], dbConfigKey=[" + dbConfigKey + "]", ex);
        }

        return this.myResultSet;
    }

    /**
     * 查询sql执行
     * @param sql
     * @return
     */
    public ResultSet executeQuery(String sql,String DBName)
    {
        try
        {
            initConnection(DBName);

            this.myResultSet = statement.executeQuery(sql);

        } catch (Exception ex) {
            //logger.error("数据库操作出错。sql=[" + sql + "], tableName=[" + tableName + "], dbConfigKey=[" + dbConfigKey + "]", ex);
        }

        return this.myResultSet;
    }


    /**
     * 执行更新sql
     * @param sql
     * @return
     */
    public int executeUpdate(String sql)
    {
        int result = 0;
        try {
            initConnection();

            result = this.statement.executeUpdate(sql);

        } catch (Exception ex) {
            //logger.error("数据库操作出错。sql=[" + sql + "], tableName=[" + tableName + "], dbConfigKey=[" + dbConfigKey + "]", ex);

            return -1;
        }
        return result;
    }

    /**
     * 执行更新sql
     * @param sql
     * @return
     */
    public int executeUpdate(String sql,String DBname)
    {
        int result = 0;
        try {
            initConnection(DBname);

            result = this.statement.executeUpdate(sql);

        } catch (Exception ex) {
            //logger.error("数据库操作出错。sql=[" + sql + "], tableName=[" + tableName + "], dbConfigKey=[" + dbConfigKey + "]", ex);

            return -1;
        }
        return result;
    }


    /**
     * 关闭数据库连接
     */
    public void close()
    {
        try
        {
            if (this.myResultSet != null) {
                this.myResultSet.close();
            }
            if (this.conn != null){
                this.conn.close();
            }
            if (this.statement != null) {
                this.statement.close();
            }

        }
        catch (SQLException e) {
            //logger.error("关闭数据库连接出错。", e);
        }
    }


    /**
     *  初始化数据源连，目前自己创建jdbc连接，后续会迁移到配置文件中
     */
    private void initConnection() throws Exception
    {

        //暂时写死地址，后续从配置文件读取
        String url = "jdbc:mysql://10.17.1.63:3306/member?useUnicode=true&characterEncoding=utf8&autoReconnect=true&&zeroDateTimeBehavior=convertToNull";

        String user = "hz-wangyinguang";
        String password = "hNglTroyIET14EX0";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.conn=DriverManager.getConnection(url,user,password);
            this.statement=this.conn.createStatement();

        }catch (ClassNotFoundException e1){
            System.out.println("数据库驱动不存在！"+e1.toString());
        }catch (SQLException e2){
            System.out.println("数据库存在异常"+e2.toString());
        }
    }

    private void initConnection(String DBname) throws Exception
    {
        String url = DBEnum.getEnumByDBname(DBname);

        //暂时写死地址，后续从配置文件读取
//        String url = "jdbc:mysql://10.17.1.63:3306/member?useUnicode=true&characterEncoding=utf8&autoReconnect=true&&zeroDateTimeBehavior=convertToNull";

        String user = "hz-wangyinguang";
        String password = "hNglTroyIET14EX0";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.conn=DriverManager.getConnection(url,user,password);
            this.statement=this.conn.createStatement();

        }catch (ClassNotFoundException e1){
            System.out.println("数据库驱动不存在！"+e1.toString());
        }catch (SQLException e2){
            System.out.println("数据库存在异常"+e2.toString());
        }
    }

}