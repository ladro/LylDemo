package com.lyldemo.utils;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lyldemo.common.Constant;
import com.lyldemo.entity.UserAllInfo;
import com.lyldemo.listener.CheckLoginListener;

import java.lang.reflect.Type;

/**
 * Created by Jade.Liu on 2017/8/17.
 */

public class UserInfoUtils {
    public static final String INTENT_ONLOGIN_SUCCESSFULLY_CALLBACK = "INTENT_ONLOGIN_SUCCESSFULLY_CALLBACK";
    public static final String INTENT_ONLOGIN_CALLBACK_PARAMS = "INTENT_ONLOGIN_CALLBACK_PARAMS";

    /**
     * 判断是否登录并回调
     *
     * @param
     * @param loginClass
     * @param listener
     * @param bundle
     */
    public static void checkLogin(Context context, Class<?> loginClass, CheckLoginListener listener, Bundle bundle) {
        if (!isLogin()) {
            Intent intent = new Intent(context, loginClass);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(INTENT_ONLOGIN_SUCCESSFULLY_CALLBACK, listener);
            intent.putExtra(INTENT_ONLOGIN_CALLBACK_PARAMS, bundle);
            context.startActivity(intent);
        } else {
            listener.OnLogined(getUserInfo(), bundle);
        }
    }


    public static void cacheLogin(boolean isLogin) {
        MySharedCache.put(Constant.REMEMBER_LOGIN, isLogin);
    }

    public static boolean isLogin() {
        return MySharedCache.get(Constant.REMEMBER_LOGIN, false);
    }

    public static void cacheAuth(String authStr) {
        MySharedCache.put(Constant.AUTH_COOKIE_NAME, authStr);
    }

    public static String getAuth() {
        return MySharedCache.get(Constant.AUTH_COOKIE_NAME, "");
    }

    public static void clearAuth() {
        MySharedCache.put(Constant.AUTH_COOKIE_NAME, "");
        MySharedCache.put(Constant.REMEMBER_LOGIN, false);
    }

    public static void clearUserInfo() {
        MySharedCache.put(Constant.USER_INFO, "");
    }


    public static void cacheUserInfo(UserAllInfo userAllInfo) {
        Gson gson = new Gson();
        String userInfoStr = gson.toJson(userAllInfo);
        MySharedCache.put(Constant.USER_INFO, userInfoStr);
    }

    public static UserAllInfo getUserInfo() {
        UserAllInfo resultData = new UserAllInfo();
        String userInfoStr = MySharedCache.get(Constant.USER_INFO, "");
        if (!TextUtils.isEmpty(userInfoStr)) {
            Gson gson = new Gson();
            Type type = new TypeToken<UserAllInfo>() {
            }.getType();
            resultData = gson.fromJson(userInfoStr, type);

        }
        return resultData;

    }
}
