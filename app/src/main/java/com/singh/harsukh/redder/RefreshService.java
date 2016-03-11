package com.singh.harsukh.redder;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.singh.harsukh.redder.model.RedditRCModel;

import java.util.Calendar;

/**
 * Created by harsukh on 3/9/16.
 */
public class RefreshService extends Service {

    private static String access_token = null;
    private static SharedPreferences mPreferences;
    private static Handler mHandler = new Handler();
    public void RefreshServiceIntializer(SharedPreferences preferences, String access_token)
    {
        mPreferences = preferences;
        this.access_token = access_token;
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
        model.refreshToken(access_token);
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
            while((target_time - System.currentTimeMillis()) > 300000) //
            {
                synchronized (this){
                    try {
                        this.wait(240000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            //using handler instead
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    refreshToken();
                }
            });
        }


    }
}
