package com.singh.harsukh.redder;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.singh.harsukh.redder.model.RedditRCModel;

import java.util.Calendar;

/**
 * Created by harsukh on 3/9/16.
 */
public class RefreshService extends Service {

    private static String access_token = null;
    private static String refresh_token = null;
    private static SharedPreferences mPreferences;
    private static Handler mHandler = new Handler();
    public void initialize(SharedPreferences preferences, String access_token, String refresh_token)
    {
        mPreferences = preferences;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder
    {
        public RefreshService getService()
        {
            return RefreshService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void start_timer_task()
    {
        TimerThread thread = new TimerThread();
        thread.start();

    }

    public void refreshToken()
    {
        RedditRCModel model = new RedditRCModel();
        model.refreshToken(refresh_token, "ReMLibe7JwFH0Q", "");
        start_timer_task();
    }

    public static void refresh(String token)
    {
        access_token = token; //refreshed here
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.putString("access_token", token);
        edit.apply();
    }

    public class TimerThread extends Thread
    {
        private long target_time;

        public TimerThread()
        {
            target_time = System.currentTimeMillis() + 3600*1000;
        }

        @Override
        public void run() {
            while((target_time - System.currentTimeMillis()) > (300*1000)) //
            {
                synchronized (this){
                    try {
                        this.wait(240*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            //using handler instead
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Log.e("RefreshService", "refreshing with" + refresh_token);
                    refreshToken();
                }
            });
        }
    }
}
