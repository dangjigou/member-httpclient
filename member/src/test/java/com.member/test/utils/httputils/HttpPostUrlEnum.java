package com.member.test.utils.httputils;

/**
 * Created by jackson on 15/11/24.
 */
public enum HttpPostUrlEnum {
    SDB_GETSHOPITEMSNUM_URL("http://daily.52shangou.com/itemcenter/seller/open/getShopItemsNum","闪电帮类目获取商品数量","JSON"),

    SDG_PAGEV2_URL("http://daily.52shangou.com/itemcenter/buyer/open/item/categoryItem/page_v2.jsonp","类目初始化","null"),

    SDG_INITV2_URL("http://daily.52shangou.com/itemcenter/buyer/open/item/categoryItem/init_v2.jsonp","类目初始化","null"),

    SDG_MULTI_CHILDCATS_PAGE_V2_URL("http://daily.52shangou.com/itemcenter/buyer/open/item/categoryItem/multi_childcats_page_v2.jsonp","类目初始化","null"),

    SDG_GETHOTITEMS_URL("http://daily.52shangou.com/itemcenter/buyer/open/item/hot.jsonp","获取热销排行商品","null"),

    SDG_ADDLABELTYPE_URL("http://daily.52shangou.com/itemcenter/crm/itemlabel/label_type/add.jsonp","添加标签类型","null"),

    SDG_ADDLABEL_URL("http://daily.52shangou.com/itemcenter/crm/itemlabel/label/add.jsonp","添加标签","null"),

    SDG_DELETELABEL_URL("http://daily.52shangou.com/itemcenter/crm/itemlabel/label/delete.jsonp","删除标签","null"),

    SDG_GETLABELTREE_URL("http://daily.52shangou.com/itemcenter/crm/itemlabel/tree.jsonp","获取标签树","null"),

    SDG_LABELBINDSPU_URL("http://daily.52shangou.com/itemcenter/crm/itemlabel/label_relation/label_bind.jsonp","标签绑定spu","null"),

    SDG_SPUBINDLABEL_URL("http://daily.52shangou.com/itemcenter/crm/itemlabel/label_relation/spu_bind.jsonp","spu绑定标签","null"),

    SDG_MLTIBIND_URL("http://daily.52shangou.com/itemcenter/crm/itemlabel/label_relation/many_to_many_bind.jsonp","spu多对多绑定标签","null"),

    SDG_BATCHUNBINDSPU_URL("http://daily.52shangou.com/itemcenter/crm/itemlabel/label_relation/batch_unbind_spu.jsonp","批量解绑标签解绑的spu","null"),

    SDG_BATCHUNBINDLABEL_URL("http://daily.52shangou.com/itemcenter/crm/itemlabel/label_relation/batch_unbind_label.jsonp","批量解绑spu绑定的标签","null"),

    SDG_GETLABELSPUINFO_URL("http://daily.52shangou.com/itemcenter/crm/itemlabel/label/spu_info.jsonp","获取标签信息,包含绑定的spu信息","null"),

    SDG_GETLABELDETAIL_URL("http://daily.52shangou.com/itemcenter/crm/itemlabel/label/detail.jsonp","获取标签详情,包含标签绑定的spu列表","null"),

    SDG_MYFREQUENTBUY_URL("http://daily.52shangou.com/itemcenter/sdg/api/myfrequentbuy.json","我常买","null"),

    SDG_RECOMMEND_URL("http://daily.52shangou.com/itemcenter/buyer/open/item/recommend.json","推荐商品","null"),

    SDG_GETFINEQUALITYITEMS_URL("http://daily.52shangou.com/itemcenter/buyer/open/item/fine_quality.json","推荐商品","null"),

    SDG_GETBOXUNITITEMS_URL("http://daily.52shangou.com/itemcenter//buyer/open/item/box_unit.json","整箱购","null"),

    SDG_GETBOXUNITITEMSCOUNT_URL("http://daily.52shangou.com/itemcenter//buyer/open/item/box_unit/count.json","整箱购数量","null"),

    SDG_IMPORTLABELRELATION_URL("http://daily.52shangou.com/itemcenter/crm/itemlabel/label_relation/import.json","导入标签绑定","JSON"),

    SDG_SUBMITPARTNER_URL("http://daily.52shangou.com/member/submitPartnerInfo.do","商家申请加盟","JSON"),

    SDG_LOGIN_URL("http://daily.52shangou.com/member/login.do","登录地址","JSON"),

    SDG_CREATEORGANIZEADMIN_URL("http://daily.52shangou.com/member/createOrganizeAdmin.do","创建集团账号","JSON"),

    SDG_LOGINFORWMT_URL("http://daily.52shangou.com/member/wmt_login.do","外卖通登录地址","JSON"),

    SDG_LOGOUT_URL("http://daily.52shangou.com/member/logout.do","退出地址","JSON"),

    SDG_GETLOGINSTATUS_URL("http://daily.52shangou.com/member/getLoginStatus.do","判断当前用户是否已登录","null"),

    SDG_LOGOUTALL_URL("http://daily.52shangou.com/member/logout_all.do","退出所有角色地址","null"),

    SDG_GETUSERINFO_URL("http://daily.52shangou.com/member/getUserInfo.do","获取当前登陆用户信息","null"),

    SDG_GETUSERBYUSERID_URL("http://daily.52shangou.com/member/getUserbyUserId.do","根据用户ID取用户信息","null"),

    SDG_GETUSERBYMOBILE_URL("http://daily.52shangou.com/member/getUserbyMobile.do","根据用户手机号取用户信息","null"),

    SDG_GETUSERBYMOBILEFORCS_URL("http://daily.52shangou.com/member/getUserbyMobileForCS.do","根据用户手机号取用户信息、店铺信息","null"),

    SDG_SENDAUTHCODEV1_URL("http://daily.52shangou.com/member/sendAuthCodeV1.do","获取验证码",null),

    SDG_GETREDISAUTHCODE_URL("http://daily.52shangou.com/member/getRedisAuthCode.do","发送验证码(假发送)",null),

    SDG_RESETBUYERPWD_URL("http://daily.52shangou.com/member/resetBuyerPWD.do","买家修改密码","JSON"),

    SDG_RESETPWD_URL("http://daily.52shangou.com/member/reset_pwd.do","卖家/cmr修改密码","JSON"),

    SDG_RESETWMTPWD_URL("http://daily.52shangou.com/member/reset_wmt_pwd.do","外卖通修改密码","JSON"),

    SDG_GETADDRESSBOOKBYSHOPS_URL("http://daily.52shangou.com/member/getAddressBookByShops.do","获取用户地址列表,区分可配送和不可配送",null),

    SDG_GETUSERADDRESSBYID_URL("http://daily.52shangou.com/member/getUserAddressById.do","根据ID获取地址",null),

    SDG_GETLOGISTICSADDRESSBYID_URL("http://daily.52shangou.com/member/getLogisticsAddressById.do","根据ID获取物流地址",null),

    SDG_GETUSERADDRESSCOUNT_URL("http://daily.52shangou.com/member/getUserAddressCount.do","获取用户地址数量",null),

    SDG_GETUSERADDRESSLIST_URL("http://daily.52shangou.com/member/getUserAddressList.do","获取用户地址列表",null),

    SDG_GETDEFAULTADDRESS_URL("http://daily.52shangou.com/member/getDefaultAddress.do","获取用户默认地址",null),

    SDG_REGISTER_URL("http://daily.52shangou.com/member/register.do","注册账号","JSON"),

    SDG_REGISTERWMTSELLER_URL("http://daily.52shangou.com/member/register_wmt_seller.do","外卖通注册账号","JSON"),

    SDG_GETUSERCOMMENTBYID_URL("http://daily.52shangou.com/member/getUserCommentById.do","根据ID获取意见反馈","null"),

    SDG_GETUSERCOMMENTBYUSERID_URL("http://daily.52shangou.com/member/getUserCommentByUserId.do","获取用户意见反馈","null"),

    SDG_UPLOADIMAGES_URL("http://daily.52shangou.com/member/uploadImages.do","上传图片","JSON"),

    SDG_ADDMEMBER_URL("http://daily.52shangou.com/member/addMember.do","添加会员","JSON"),

    SDG_GETMEMBERINFO_URL("http://daily.52shangou.com/member/getMemberInfo.do","获取会员信息",null),

    SDG_ISMEMBERPASSWORD_URL("http://daily.52shangou.com/member/isMemberPassword.do","校验会员密码",null),

    SDG_GETSHOPDISCOUNTTEMPLATE_URL("http://daily.52shangou.com/member/getShopDiscountTemplate.do","获取店铺优惠规则",null),

    SDG_GETADDRESSAFTERVERIFYCODE_URL("http://daily.52shangou.com/member/getAddressAfterVerifyCode.do","校验验证码并返回地址列表",null),

    SDG_SAVESHOPDISCOUNTTEMPLATE_URL("http://daily.52shangou.com/member/saveShopDiscountTemplate.do","保存店铺优惠规则","JSON"),

    SDG_UPDATEMEMBER_URL("http://daily.52shangou.com/member/updateMember.do","修改会员","JSON"),

    SDG_UPDATEMEMBERPASSWORD_URL("http://daily.52shangou.com/member/updateMemberPassword.do","修改会员密码","JSON"),

    SDG_UPDATEMEMBERMOBILE_URL("http://daily.52shangou.com/member/updateMemberMobile.do","修改会员手机号","JSON"),

    RETAIL_LOGIN("https://dailyka.52shangou.com/member/butler_login.do","一号管家登录","JSON"),

    RETAIL_SHELF_LIST("https://dailyka.52shangou.com/itemcenter/ka/sku/shelf/list","一号管家集团获取前台类目","null"),

    RETAIL_SHELF_UPDATE("https://dailyka.52shangou.com/itemcenter/ka/sku/shelf/update","一号管家集团更新前台类目","JSON"),

    RETAIL_SHELF_CREATE("https://dailyka.52shangou.com/itemcenter/ka/sku/shelf/create","一号管家集团创建前台类目","JSON"),

    RETAIL_SEARCH_BY_SHOPS("https://dailyka.52shangou.com/itemcenter/ka/item/searchByShops","一号管家集团多店铺商品查询接口","null"),

    RETAIL_LOGIN_GRAYKA("https://grayka.52shangou.com/member/butler_login.do","一号管家预发环境登录","JSON"),

    RETAIL_MULTIPLE_UPDATE_PRICE("http://grayka.52shangou.com/itemcenter/ka/item/import/multipleUpdatePrice","一号管家集团创建前台类目","JSON"),

    ITEMCENTER_UPLOADIMAGE_URL("http://daily.manage.51xianqu.com/ib/crm/baseSpu/uploadImage", "上传图片", "JSON"),

    CRM_LOGIN_URL("http://daily.manage.51xianqu.com/member/login.do","登录地址","JSON")

    ;





    /**
     * 类型
     */
    private String url;

    /**
     * 名称
     */
    private String name;

    /**
     * post类型，默认为null，如果为json泽为“JSON”
     */
    private String type;

    /**
     * 构造函数
     * @param url
     * @param name
     * @param type
     */
    HttpPostUrlEnum(String url, String name, String type) {
        this.url = url;
        this.name = name;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getType(){
        return  type;
    }

    public static String getTypeByUrl(String url){

        for (HttpPostUrlEnum httpPostUrlEnum:HttpPostUrlEnum.values()){
            if (httpPostUrlEnum.getUrl().equals(url)){
                return  httpPostUrlEnum.getType();
            }
        }
        return  null;
    }

}
