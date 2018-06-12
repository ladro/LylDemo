package com.lyldemo.entity;

/**
 * Created by KJT-30 on 2018/6/12.
 */

public class WeatherInfo {

    private WeatherInfoBean weatherinfo;

    public WeatherInfoBean getWeatherinfo() {
        return weatherinfo;
    }

    public void setWeatherinfo(WeatherInfoBean weatherinfo) {
        this.weatherinfo = weatherinfo;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "weatherinfo=" + weatherinfo +
                '}';
    }
}
