package com.lyldemo.activity;

import android.util.Log;
import android.widget.TextView;

import com.lyldemo.base.BaseActivity;
import com.lyldemo.base.BaseApp;
import com.lyldemo.entity.WeatherInfo;
import com.rxretrofitdemo.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by KJT-30 on 2018/6/4.
 */
@ContentView(R.layout.activity_two)
public class ActivityTwo extends BaseActivity {
    private Map<String, String> headers = new HashMap<>();
    @ViewInject(R.id.tv2)
    private TextView view;


    @Override
    public void initView() {
        initTitleBar(true, "retrofit网络请求");
    }

    @Override
    public void getData() {
        getHttpData();
    }

    public void getHttpData() {
        //    @Headers({"User-Agent:BauhiniaValley Android/1.0",
//            "Accept-Language:zh-CN",
//            "Cookie:BauhiniaValleyAuth=dame4EV3YYyKB2nAvCCDD+mnlfeRduwXOWPwWAXTF4R41ARV01GsGRjaJiDcBjgC"
//    })
        headers.put("User-Agent", "BauhiniaValley Android/1.0");
        headers.put("Accept-Language", "zh-CN");
        headers.put("Cookie", "BauhiniaValleyAuth=dame4EV3YYyKB2nAvCCDD+mnlfeRduwXOWPwWAXTF4R41ARV01GsGRjaJiDcBjgC");
        BaseApp.instance().
                getApiNetService().getWeatherInfo(headers).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                subscribe(new DefaultObserver<Response<WeatherInfo>>() {
                    @Override
                    public void onNext(Response<WeatherInfo> value) {
                        Log.e("tag_onNext", "tag_onNext:" + value.body().getWeatherinfo());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("tag_onError", "tag_onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("tag_onComplete", "tag_onComplete:");
                    }
                });
    }
}
