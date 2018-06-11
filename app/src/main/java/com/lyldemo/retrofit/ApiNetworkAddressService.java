package com.lyldemo.retrofit;


import com.lyldemo.entity.TypeInfo;
import com.lyldemo.retrofit.entity.RetrofitResultData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

}
