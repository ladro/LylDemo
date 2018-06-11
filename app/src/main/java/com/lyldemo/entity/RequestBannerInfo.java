package com.lyldemo.entity;


import com.lyldemo.common.BannerImgLocationId;
import com.lyldemo.common.BannerImgPageId;

/**
 * Created by Jade.Liu on 2017/10/25.
 */

public class RequestBannerInfo {
    private BannerImgPageId pageId;
    private BannerImgLocationId postion;


    public RequestBannerInfo(BannerImgPageId pageId, BannerImgLocationId postion) {
        this.pageId = pageId;
        this.postion = postion;
    }

    public BannerImgPageId getPageId() {
        return pageId;
    }

    public void setPageId(BannerImgPageId pageId) {
        this.pageId = pageId;
    }

    public BannerImgLocationId getPostion() {
        return postion;
    }

    public void setPostion(BannerImgLocationId postion) {
        this.postion = postion;
    }
}
