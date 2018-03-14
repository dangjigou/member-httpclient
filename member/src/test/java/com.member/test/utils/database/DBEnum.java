package com.member.test.utils.database;

/**
 * Created by jackson on 17/8/11.
 */
public enum DBEnum {
    MEMBER("member","jdbc:mysql://10.17.1.63:3306/member?useUnicode=true&characterEncoding=utf8&autoReconnect=true&&zeroDateTimeBehavior=convertToNull","member数据库"),

    ITEM("item","jdbc:mysql://10.17.1.63:3306/item?useUnicode=true&characterEncoding=utf8&autoReconnect=true&&zeroDateTimeBehavior=convertToNull","item数据库"),

    ITEMCENTER("itemcenter","jdbc:mysql://10.17.1.63:3306/itemcenter?useUnicode=true&characterEncoding=utf8&autoReconnect=true&&zeroDateTimeBehavior=convertToNull","item数据库")
    ;

    /**
     * 类型
     */
    private String DBname;

    /**
     * 类型
     */
    private String url;

    /**
     * 名称
     */
    private String name;


    /**
     * 构造函数
     * @param url
     * @param name
     */
    DBEnum(String DBname, String url, String name) {
        this.DBname = DBname;
        this.url = url;
        this.name = name;
    }


    public String getDBname() {
        return DBname;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public static String getEnumByDBname(String DBname){
        for(DBEnum dbEnum : DBEnum.values()){
            if(dbEnum.getDBname()== DBname){
                return dbEnum.getUrl();
            }
        }
        return null;
    }
}
