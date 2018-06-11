package com.lyldemo.common;

/**
 * Created by Jade.Liu on 2017/10/25.
 */

public enum BannerImgLocationId {
    LOCATION_HEAD(1);//头部

    private final int value;

    BannerImgLocationId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
