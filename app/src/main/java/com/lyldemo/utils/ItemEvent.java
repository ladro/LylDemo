package com.lyldemo.utils;

/**
 * item事件
 */
public class ItemEvent {
    public ItemEvent(String result) {
        this.result = result;
    }

    public enum ACTIVITY {
        /**
         * 装车列表刷新
         */
        ENTRUCK_AC,
    }

    public enum ACTION {
        /**
         * 装车列表刷新
         */
        ENTRUCK_DATA,
    }

    public ItemEvent() {
    }

    public ItemEvent(ACTIVITY activity, ACTION action) {
        this.activity = activity;
        this.action = action;
    }

    public ItemEvent(ACTIVITY activity, ACTION action, String result) {
        this.activity = activity;
        this.action = action;
        this.result = result;
    }

    private ACTIVITY activity;

    private ACTION action;

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ACTIVITY getActivity() {
        return activity;
    }

    public void setActivity(ACTIVITY activity) {
        this.activity = activity;
    }

    public ACTION getAction() {
        return action;
    }

    public void setAction(ACTION action) {
        this.action = action;
    }


}
