package com.lyldemo.retrofit;

/**
 * Created by KJT-30 on 2018/6/4.
 */

public class RetrofitHttpUtils {
    public  static ApiNetworkAddressService apiNetService;

    public static ApiNetworkAddressService getApiNetService() {
        if (apiNetService == null) {
            apiNetService = BaseRetrofitFactory.getApiService();
        }
        return apiNetService;
    }
}
