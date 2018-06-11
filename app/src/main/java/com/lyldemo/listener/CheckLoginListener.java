package com.lyldemo.listener;

import android.os.Bundle;
import android.os.Parcelable;

import com.lyldemo.entity.UserAllInfo;


public interface CheckLoginListener extends Parcelable {
	public void OnLogined(UserAllInfo customer, Bundle bundle);
}
