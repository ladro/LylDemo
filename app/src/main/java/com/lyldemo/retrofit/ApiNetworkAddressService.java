package com.lyldemo.retrofit;


import com.lyldemo.entity.TypeInfo;
import com.lyldemo.entity.WeatherInfo;
import com.lyldemo.retrofit.entity.RetrofitResultData;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

/**
 * 项目名称:Retrofit2.0+RxJava+RxAndroid
 * 创建人:kexiang
 * 创建时间:2016/9/28 14:37
 */
public interface ApiNetworkAddressService {

    /**
     * 获取物流类型列表
     */
    @FormUrlEncoded
    @POST("Load/GetShipTypeList")
    Observable<Response<RetrofitResultData<List<TypeInfo>>>> getShipTypeList(
            @Field("token") String token
//            @Field("type") String type
    );

    /**
     * 获取天气信息，带请求头，2种方式，静态设置和代码动态设置
     */
//    @Headers({"User-Agent:BauhiniaValley Android/1.0",
//            "Accept-Language:zh-CN",
//            "Cookie:BauhiniaValleyAuth=dame4EV3YYyKB2nAvCCDD+mnlfeRduwXOWPwWAXTF4R41ARV01GsGRjaJiDcBjgC"
//    })
    @GET("data/sk/101190408.html")
    Observable<Response<WeatherInfo>> getWeatherInfo(
            @HeaderMap Map<String, String> headers
    );
}
