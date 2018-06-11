package com.lyldemo.common;

/**
 * Created by Jade.Liu on 2017/10/25.
 */

public enum BannerImgPageId {
    HOME(1), //首页
    NEWS(2), //新闻
    ACTIVE(3), //活动
    TOPIC(4),//话题
    QUESTION(5),//提问
    ANSWER(6);//回答
    private final int value;

    BannerImgPageId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
