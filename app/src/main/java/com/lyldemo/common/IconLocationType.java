package com.lyldemo.common;

/**
 * Created by Jade.Liu on 2017/8/16.
 */

public enum IconLocationType {
    LEFT(0), TOP(1), RIGHT(2), BOTTOM(3);
    private final int value;

    IconLocationType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
