package com.lyldemo.utils;


import android.content.Context;

import com.tencent.tauth.Tencent;

public class TencentUtil {
	private static String APP_TENCENT_ID="1106654700";
	
	public static Tencent initTencentAPI(Context context) {
		
		return Tencent.createInstance(APP_TENCENT_ID, context);
	}
}
