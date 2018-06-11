package com.lyldemo.retrofit;

/**
 * 项目名称:queued
 * 创建人:kexiang
 * 创建时间:2018-03-28 16:20
 */

public class FieldsDict {
    /**
     * 状态  0  全部  1 未同意  2  已同意
     */
    public static final int RESERVE_ALL = 0;
    /**
     * 状态  0  全部  1 未同意  2  已同意
     */
    public static final int RESERVE_NO = 1;
    /**
     * 状态  0  全部  1 未同意  2  已同意
     */
    public static final int RESERVE_PASS = 2;

    /**
     * 1 同意  2  拒绝
     */
    public static final int RESERVE_CONSENT = 1;
    /**
     * 1 同意  2  拒绝
     */
    public static final int RESERVE_NOT = 2;

    /**
     * 状态  0  全部  1 未同意  2  已同意
     */
    public static final int ALL_ORDER = 0;
    /**
     * 状态  0  全部  1 未同意  2  已同意
     */
    public static final int NO_ORDER = 1;
    /**
     * 状态  0  全部  1 未同意  2  已同意
     */
    public static final int PASS_ORDER = 2;


    /**
     * 状态  0 全部  1 已接单  2  已拒绝 3 配送中 4 完成
     */
    public static final int TAKEOUT_ALL = 0;
    /**
     * 状态  0 全部  1 已接单  2  已拒绝 3 配送中 4 完成
     */
    public static final int TAKEOUT_PASS = 1;

    /**
     * 状态  0 全部  1 已接单  2  已拒绝 3 配送中 4 完成
     */
    public static final int TAKEOUT_YJJ = 2;

    /**
     * 状态  0 全部  1 已接单  2  已拒绝 3 配送中 4 完成
     */
    public static final int TAKEOUT_SEND = 3;

    /**
     * 状态  0 全部  1 已接单  2  已拒绝 3 配送中 4 完成
     */
    public static final int TAKEOUT_OK = 4;

    /**
     * 0 男  1  女
     */
    public static final String MAN = "0";


    /**
     * 0 男  1  女
     */
    public static final String WOMAN = "1";

    /**
     * 全部
     */
    public static final int STATUS_ALL = -1;

    /**
     * 用户下单
     */
    public static final int STATUS_0 = 0;

    /**
     * 用户支付
     */
    public static final int STATUS_1 = 1;
    /**
     * 商家接受
     */
    public static final int STATUS_3 = 3;
    /**
     * 商家拒绝
     */
    public static final int STATUS_4 = 4;

    /**
     * 用户取消
     */
    public static final int STATUS_5 = 5;

    /**
     * 用户取消
     */
    public static final int STATUS_6 = 6;

    /**
     * 商家取消
     */
    public static final int STATUS_7 = 7;

    /**
     * 完成
     */
    public static final int STATUS_8 = 8;

    /**
     * 退款中
     */
    public static final int STATUS_9 = 9;
    /**
     * 删除
     */
    public static final int STATUS_10 = 10;

    /**
     * 等待配送
     */
    public static final int STATUS_11 = 11;

    /**
     * 配送中
     */
    public static final int STATUS_12 = 12;

    /**
     * 1--预约  2--点餐  3--外卖  4--系统消息
     */
    public static final int MQ_Reserve = 1;

    /**
     * 1--预约  2--点餐  3--外卖  4--系统消息
     */
    public static final int MQ_ORDER = 2;

    /**
     * 1--预约  2--点餐  3--外卖  4--系统消息
     */
    public static final int MQ_TAKEOUT = 3;
    /**
     * 1.新订单  2  取消订单  3催单
     */
    public static final int MQ_ADD = 1;


}
