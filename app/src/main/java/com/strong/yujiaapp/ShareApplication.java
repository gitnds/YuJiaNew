package com.strong.yujiaapp;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.mob.MobApplication;
import com.strong.yujiaapp.service.LocationService;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

/**
 * ȫ�ֱ���
 * 
 * @author Administrator
 * 
 */
public class ShareApplication extends MobApplication {
	public static final String APP_ID = "2882303761517613670";
    public static final String APP_KEY = "5761761365670";
    public static final String TAG = "com.strong.yujiaapp";
    public LocationService locationService;
    public Vibrator mVibrator;
	@Override
	public void onCreate() {
		super.onCreate();
        // 创建百度地图对象
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
        //初始化push推送服务
        if(shouldInit()) {
            MiPushClient.registerPush(this, APP_ID, APP_KEY);
        }
        //打开Log
        LoggerInterface newLogger = new LoggerInterface() {

            @Override
            public void setTag(String tag) {
                // ignore   
            }

            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, content, t);
            }

            @Override
            public void log(String content) {
                Log.d(TAG, content);
            }
        };
        Logger.setLogger(this, newLogger);

    }


    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
	}

}
