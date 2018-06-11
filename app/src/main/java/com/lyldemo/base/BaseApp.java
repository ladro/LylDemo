package com.lyldemo.base;

import android.app.Application;

import com.lyldemo.retrofit.ApiNetworkAddressService;
import com.lyldemo.retrofit.BaseRetrofitFactory;

public class BaseApp extends Application {
    private static BaseApp instance;
    protected ApiNetworkAddressService apiNetService;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public  static BaseApp instance() {
        return instance;
    }
    public ApiNetworkAddressService getApiNetService() {
        if (apiNetService==null){
            apiNetService = BaseRetrofitFactory.getApiService();
        }
        return apiNetService;
    }
}
