package com.lyldemo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ViCTor-PC on 2017/10/18.
 */

public class UserAllInfo implements Serializable {


    private String weChatOpenId;
    private String qqOpenId;
    private boolean hasBindWeChat;
    private boolean hasBindQQ;
    private String channelId;
    private String avatarUrl;
    private int deviceType;
    private String email;
    private String fromLinkSource;
    private int gender;
    private String lastLoginTime;
    private String name;
    private String note;
    private String phone;
    private int status;
    private int sysNo;
    private int attestationType;//0未认证 1导师 2学生
    private int foucsed;
    private int  focusStatus;
    private int attestationStatus;
    @SerializedName("ThirdPartyUserSysNo")
    private int ThirdPartyUserSysNo;
    private boolean hasBindPhone;

    public String getWeChatOpenId() {
        return weChatOpenId;
    }

    public void setWeChatOpenId(String weChatOpenId) {
        this.weChatOpenId = weChatOpenId;
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    public int getFocusStatus() {
        return focusStatus;
    }

    public void setFocusStatus(int focusStatus) {
        this.focusStatus = focusStatus;
    }

    public boolean isHasBindWeChat() {
        return hasBindWeChat;
    }

    public void setHasBindWeChat(boolean hasBindWeChat) {
        this.hasBindWeChat = hasBindWeChat;
    }

    public boolean isHasBindQQ() {
        return hasBindQQ;
    }

    public void setHasBindQQ(boolean hasBindQQ) {
        this.hasBindQQ = hasBindQQ;
    }

    public boolean isHasBindPhone() {
        return hasBindPhone;
    }

    public void setHasBindPhone(boolean hasBindPhone) {
        this.hasBindPhone = hasBindPhone;
    }

    public int getThirdPartyUserSysNo() {
        return ThirdPartyUserSysNo;
    }

    public void setThirdPartyUserSysNo(int thirdPartyUserSysNo) {
        ThirdPartyUserSysNo = thirdPartyUserSysNo;
    }

    public int getAttestationStatus() {
        return attestationStatus;
    }

    public void setAttestationStatus(int attestationStatus) {
        this.attestationStatus = attestationStatus;
    }

    public int getAttestationType() {
        return attestationType;
    }

    public void setAttestationType(int attestationType) {
        this.attestationType = attestationType;
    }

    public int getFoucsed() {
        return foucsed;
    }

    public void setFoucsed(int foucsed) {
        this.foucsed = foucsed;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFromLinkSource() {
        return fromLinkSource;
    }

    public void setFromLinkSource(String fromLinkSource) {
        this.fromLinkSource = fromLinkSource;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatus() {
        return status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSysNo() {
        return sysNo;
    }

    public void setSysNo(int sysNo) {
        this.sysNo = sysNo;
    }
}
