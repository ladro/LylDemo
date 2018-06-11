package com.lyldemo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jade.Liu on 2017/8/14.
 */

public class BannerInfo implements Serializable {
    @SerializedName("bannerId")
    private String bannerId;
    @SerializedName("href")
    private String href;
    @SerializedName("imageSrc")
    private List<String> imageSrc;
    @SerializedName("title")
    private String title;

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<String> getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(List<String> imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
