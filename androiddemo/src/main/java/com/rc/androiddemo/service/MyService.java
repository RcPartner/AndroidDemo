package com.rc.androiddemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import com.rc.androiddemo.IMyServiceInterface;

public class MyService extends Service {

    private Holder iBinder = new Holder();

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(MyService.this, "My Service Destroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startMyService();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return iBinder;
    }

    private void startMyService() {
        Toast.makeText(MyService.this, "startService for onStartCommand", Toast.LENGTH_SHORT).show();
    }

    private void bindMyService(String id) {
        Toast.makeText(MyService.this, "startService for Bind, id is : " + id, Toast.LENGTH_SHORT).show();
    }

    private void stopMyService() {
        Toast.makeText(MyService.this, "stopService", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent("myservice");
        intent.setPackage(getPackageName());
        stopService(intent);
    }

    private final class Holder extends IMyServiceInterface.Stub {
        @Override
        public void stopService() throws RemoteException {
            stopMyService();
        }

        @Override
        public void startService(String id) throws RemoteException {
            bindMyService(id);
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }
}
