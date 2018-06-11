package com.lyldemo.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lyldemo.base.UrlFields;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 项目名称:Retrofit2.0+RxJava+RxAndroid
 * 创建人:kexiang
 * 创建时间:2016/9/28 15:24
 */
public class BaseRetrofit {


    ApiNetworkAddressService apiService;
    public static String getInputString(Object input) {
        String inputStr = gson.toJson(input);
//        LogUtils.toJsonFormat("inputString", inputStr);
        return inputStr;
    }
    public final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .serializeNulls()
            .create();


    BaseRetrofit() {

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient =
                new OkHttpClient.Builder()
                        .retryOnConnectionFailure(true)
//                        .addInterceptor(sLoggingInterceptor)
//                        .addInterceptor(httpLoggingInterceptor)
                        .addNetworkInterceptor(httpLoggingInterceptor)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .build();

        Retrofit retrofit =
                new Retrofit.Builder()
                        .client(okHttpClient)
                        .baseUrl(UrlFields.URL_BASE)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

        apiService = retrofit.create(ApiNetworkAddressService.class);

    }

    /**
     * 设置打印接口
     */
    private HttpLoggingInterceptor httpLoggingInterceptor =
            new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
//                    LogUtils.toJsonFormat("okhttpBack", message);
                }
            });


    /**
     * 打印返回的json数据拦截器
     */
    private static final Interceptor sLoggingInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request();
            Buffer requestBuffer = new Buffer();
            if (request.body() != null) {
                request.body().writeTo(requestBuffer);
            }

            return chain.proceed(request);
        }
    };


    public ApiNetworkAddressService getApiService() {
        return apiService;
    }
}
