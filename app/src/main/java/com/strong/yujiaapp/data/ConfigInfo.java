package com.strong.yujiaapp.data;

/**
 * Created by Administrator on 2017/8/24.
 */

public class ConfigInfo {
    /**
     * 更改密码
     */
    public static final String CHANGE_PASSWORD = "UserChangePassword";
    /**
     * 登录
     */
    public static final String LOGIN_USERLOGIN = "UserLogin";
    /**
     * 判断是否为最新版本
     */
    public static final String LOAD_SELECTIONVERSION = "SelectionVersion";
    /**
     * 判断是否已注册
     */
    public static final String LOAD_GETPHONEREGISTER = "GetPhoneRegister";
    /**
     * 注册设备
     */
    public static final String LOAD_PHONEREGISTER = "PhoneRegister";
    /**
     * 网络地址
     */
    public static final String URL = "http://175.25.16.107/yjweb/";

    /**
     * webservice名称
     */
    public static final String WEBSERVICE = "WebServiceAndroidUpdate.asmx";

    /**
     * webservice命名空间
     */
    public static final String NAMESPACE = "http://tempuri.org/";
    // 更新
    /**
     * 更新文件存放目录
     */
    public static final String UPDATE_SERVER = "UpdateFile/";
    /**
     * 更新服务器文件名
     */
    public static final String UPDATE_APKNAME = "yjwl_2.apk";
    /**
     * 更新文件保存本地文件名
     */
    public static final String UPDATE_SAVENAME = "yjwl.apk";
    /**
     * 当前命名空间
     */
    public static final String PACKAGE = "com.strong.yjwl";
    // 功能
    /**
     * 数据库存储路径
     */
    public static final String FILE_PATH = "data/data/com.strong.yjwl";
    /**
     * 数据库文件名
     */
    public static final String FILE_NAME = "/android.db";
    /**
     * 点击事件间隔
     */
    public static final long INTERVAL_TIME = 2000;
    /**
     * 每次查询显示的条数
     */
    public static final int PAGE_SIZE = 20;


}
