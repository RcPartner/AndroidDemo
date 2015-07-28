package com.rc.androiddemo.service;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.rc.androiddemo.IMyServiceInterface;
import com.rc.androiddemo.R;

/**
 * Description:
 * Author: WuRuiqiang(263454190@qq.com)
 * Date: 2015-07-28 16:41
 */
public class ServiceDemo extends Activity {

    private final String ACTION = "myservice";

    private IMyServiceInterface iBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
    }

    public void startMyService(View view) {
        ((AlarmManager) getSystemService(ALARM_SERVICE)).setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(), 10000, getPendingIntent());
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(ACTION);
        intent.setPackage(getApplicationContext().getPackageName());
        return PendingIntent.getService(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void bindMyService(View view) {
        Intent intent = new Intent(ACTION);
        intent.setPackage(getApplicationContext().getPackageName());
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    public void stopMyService(View view) {
        ((AlarmManager) getSystemService(ALARM_SERVICE)).cancel(getPendingIntent());
        if (iBinder != null) {
            try {
                iBinder.stopService();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            Intent intent = new Intent(ACTION);
            intent.setPackage(getPackageName());
            stopService(intent);
        }
    }

    private final ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBinder = IMyServiceInterface.Stub.asInterface(service);
            try {
                iBinder.startService("bind");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
