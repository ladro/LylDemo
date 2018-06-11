package com.lyldemo.utils;

import android.content.Context;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.xutils.common.util.MD5;

import java.util.Random;

public class WXUtil {
    public static final String APP_WEI_XIN_ID = "wx7087122d48f50a4f";// 微信KEY


    /**
     * 初始化微信
     *
     * @param context
     * @return
     */
    public static IWXAPI initWXAPI(Context context) {
        IWXAPI wxApi = createWXAPI(context);
        wxApi.registerApp(APP_WEI_XIN_ID);

        return wxApi;
    }

    /**
     * 创建微信API
     *
     * @param context
     * @return
     */
    public static IWXAPI createWXAPI(Context context) {
        return WXAPIFactory.createWXAPI(context, APP_WEI_XIN_ID, true);
    }

    /**
     * 获取随机字符串
     *
     * @return
     */
    public static String getNonceStr() {
        Random random = new Random();
        return MD5.toHexString(String.valueOf(random.nextInt(10000)).getBytes());
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}
