package com.starter.fastweb.utils;

public class SysConstants {

    /**
     * redis 过期时间
     * USERID_SESSIONID_EXPIRED userid的sessionId过期时间：24小时
     * HAS_REMIND_EXPIRING_EXPIRED  hasRemindExpiring过期时间：24小时
     *
     */
    public final static int USERID_SESSIONID_EXPIRED = 24 * 60 * 60;

    public final static long HAS_REMIND_EXPIRING_EXPIRED = 24 * 60 * 60;


    public final static String GOODS_STATE_4 = "4";
    public final static String ORDER_STATE_5 = "5";
    public final static String WEB_UNBIND = "web端解绑";

	/**
	 * 返回码
	 */
	public final static String CODE_OK = "0";
	public final static String CODE_FAIL = "1";
	public final static String CODE_ERROR = "2";
	
	/**
	 * 登录返回消息
	 */
	public final static String LOGIN_MSG_SUCCESS = "登录成功";
	public final static String LOGIN_MSG_NO_USER = "账号或密码错误";
	public final static String LOGIN_MSG_PERMISSION_DENIED = "账号没有登录权限";
	public final static String LOGIN_MSG_USER_DISABLE = "账号已禁用，请联系管理员";
	
	/**
	 * session key
	 */
	public final static String SESSION_USER = "admin";
	
	/**
	 * 排序方式
	 */
	public final static String ASC = "asc";
	public final static String DESC = "desc";
	
	/**
	 * 历史数据标记
	 */
	//历史
	public final static int HIS = 1;
	//实时
	public final static int NOW = 0;
	//全部
	public final static int ALL = -1;
	
	/**
	 * 告警类型
	 * 1 -- 电量不足
	 * 2 -- 异常光爆
	 * 3 -- 路线异常
	 * 4 -- 快件延误
	 * 5 -- 异常停留
	 * 6 -- 震动报警
	 * 7 -- 温度报警
	 * 8 -- 湿度报警
	 * 9 -- 倾斜告警
     * 10 -- 气压告警
	 */
	public final static String WARNING_REASON_DL = "1";
	public final static String WARNING_REASON_BG = "2";
	public final static String WARNING_REASON_LX = "3";
	public final static String WARNING_REASON_YW = "4";
	public final static String WARNING_REASON_TL = "5";
	public final static String WARNING_REASON_ZD = "6";
	public final static String WARNING_REASON_WD = "7";
	public final static String WARNING_REASON_SD = "8";
	public final static String WARNING_REASON_QX = "9";
    public final static String WARNING_REASON_QY = "10";
	
	/**
	 * 数据类型  0--震动  1--震动  2--曝光  3--温度  4--湿度   5--倾斜
	 */
    public final static int POSITION_FLAG = 0;
	public final static int ACCELER_FLAG = 1;
	public final static int EXPOSURE_FLAG = 2;
	public final static int TEMPERATURE_FLAG = 3;
	public final static int HUMIDITY_FLAG = 4;
	public final static int ANGLE_FLAG = 5;
	
	public final static String ACCELER = "震动";
	public final static String EXPOSURE = "曝光";
	public final static String TEMPERATURE = "温度";
	public final static String HUMIDITY = "湿度";
	public final static String ANGLE = "倾斜";

    /**
     * 返回消息
     */
    public final static String DEVICE_NO_EXIST_OR_WORKING = "终端不存在或已绑定";
    public final static String DUPLICATE_EXPRESSID = "重复运单";
    public final static String SAVE_SUCCESS = "保存成功";
    public final static String SAVE_FAILED = "保存失败";
    public final static String REMOVE_FAILED = "删除失败";
    public final static String REMOVE_SUCCESS = "删除成功";
    public final static String CONFIRM_SUCCESS = "签收成功";
    public final static String CONFIRM_FAILED = "签收失败";
    public final static String HAS_UNCONFIRM_EXPRESS = "终端有未签收运单，不能解绑";
    public final static String UNBIND_SUCCESS = "解绑成功";
    public final static String UNBIND_FAILED = "解绑失败";
    public final static String ASSIGN_SUCCESS = "分配成功";
    public final static String ASSIGN_FAILED = "分配失败";
    public final static String UPDATE_SUCCESS = "修改成功";
    public final static String UPDATE_FAILED = "修改失败";

    public final static String ORIGINAL_PASSWORD_ERROR = "原密码错误";
    public final static String CHANGE_PASSWORD_SUCCESS = "密码修改成功";

    public final static String SYSTEM_ABNORMAL = "系统异常";

    public final static String EXPRESS_NOT_EXISTS = "运单不存在";
    public final static String EXPRESS_RECEIVED = "运单已签收";
    public final static String EXPRESS_SHIPPED = "运单已启运";
    public final static String SHIP_SUCCESS = "启运成功";

    public final static String DEVICEID_CANNOT_BE_NULL = "终端号不能为空";
    public final static String DEVICE_NO_EXIST = "终端不存在";

    public final static String EXPRESSID_OR_DEVICEID_CANNOT_BE_NULL = "运单号或终端号不能为空";
    public final static String EXPRESS_OR_DEVICE_NO_EXIST = "运单或终端不存在";

    public final static String FORBIDDEN_DELETE_HAS_SUB_FUNCTION = "不能删除含有子功能的功能";
    public final static String FUNCTION_CODE_EXISTS = "功能编码已存在";
    public final static String ROLE_CODE_EXISTS = "角色编码已存在";
    public final static String ASSOCIATE_ROLE = "关联有角色，不允许删除";

    public final static String USER_EXISTS = "用户已存在";




    /**
     * 终端服务端类型
     */
    public final static String WINDOWS_SERVER = "1";
    public final static String LINUX_SERVER = "2";
    public final static String LINUX_SERVER_NEW = "3";


    /**
     * linux终端指令类型
     * 3 解绑
     */
    public final static int LINUX_COMMAND_UNBIND = 3;


    /**
     * 电子围栏类型
     * 1 用于静默终端
     * 2 用于终端显示
     */
    public final static String FENCE_TYPE_1 = "1";
    public final static String FENCE_TYPE_2 = "2";

    /**
     * 终端状态
     * 1 工作中
     * 0 空闲中
     * 2 工作中(有数据)
     */
    public final static String DEVICE_STATE_WORKING = "2";
	public final static String DEVICE_STATE_WORK = "1";
    public final static String DEVICE_STATE_IDLE = "0";

    //function active
    // Y 可用  N 禁用
    public final static String ACTIVE_YES = "Y";
    public final static String ACTIVE_NO = "N";

    /**
     * FUNCTION_TYPE
     * MODULE、MENU、BUTTON
     */
    public final static String FUNCTION_TYPE_MODULE = "MODULE";
    public final static String FUNCTION_TYPE_MENU = "MENU";
    public final static String FUNCTION_TYPE_BUTTON = "BUTTON";

    /**
     * 开关
     * 1 开启
     * 0 关闭
     */
    public final static String ENABLE = "1";
    public final static String DISABLE = "0";


    /**
     * 启运状态
     */
    public final static String SHIPPED_YES = "1";
    public final static String SHIPPED_NO = "0";

    public final static String LOCALE_EN = "en";
    public final static String LOCALE_CN = "cn";


    /**
     * function type
     * MODULE : 模块
     * MENU ： 菜单
     * BUTTON : 按钮
     */
    public final static String FUNCTION_TPYE_MODULE = "MODULE";
    public final static String FUNCTION_TPYE_MENU = "MENU";
    public final static String FUNCTION_TPYE_BUTTON = "BUTTON";

    /**
     * 功能权限是否权限检查
     * Y : 是
     * N : 否
     */
    public final static String FUNCTION_CHECKABLE_Y = "Y";
    public final static String FUNCTION_CHECKABLE_N = "N";

    /**
     * 系统类型
     */
    public final static String SYSTEM_TYPE_WEB = "WEB";


    /**
     * 是否是叶子节点
     */
    public final static String IS_LEAF = "Y";
    public final static String NOT_LEAF = "N";

    public final static String SEQ_SEPARATOR = "/";


    public final static String DICTIONARY_LANGUAGE_CN = "zh_CN";

    /**
     * 用户默认密码
     */
    public final static String DEFAULT_PASSWORD = "123456";









	
	
	
	
}
