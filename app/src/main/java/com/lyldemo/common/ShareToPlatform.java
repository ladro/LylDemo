package com.lyldemo.common;

/**
 * Created by Jade.Liu on 2017/10/18.
 */

public enum ShareToPlatform {
    SHARE_TO_QQ(0), SHARE_TO_WECHAT(1), SHARE_TO_MOMENTS(2), SHARE_TO_QZONE(3);

    private final int value;

    ShareToPlatform(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
