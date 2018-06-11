package com.lyldemo.retrofit;

/**
 * 创建人:kexiang
 * 创建时间:2016/11/16 15:18
 * 请求返回状态号字段
 */

public class RequestStateFields {
    public static final int SUCCEE = 200;

    /**
     * 普通排队
     */
    public static final String NORMAL_QUEUED = "normal";
    /**
     * Vip排队
     */
    public static final String VIP_QUEUED = "vip";

    /**
     * 放弃
     */
    public static  final String GIVE_UP = "giveUp";
    /**
     * 绑定
     */
    public static  final String BIND = "bind";
    /**
     * 过号
     */
    public static  final String OVERDUE = "overdue";
    /**
     * 完成
     */
    public static  final String DONE = "done";

}
