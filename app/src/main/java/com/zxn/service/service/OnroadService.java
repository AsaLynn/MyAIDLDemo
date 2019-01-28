package com.zxn.service.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.zxn.service.IOnroad;

public class OnroadService extends Service {
    public OnroadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private String TAG = "OnroadService";
    private IOnroad.Stub binder = new IOnroad.Stub() {

        @Override
        public void sayHello(String name, int age) throws RemoteException {
            //--->name:say onClickage:1028Thread:Binder:5786_4
            final String text = "sayHello: --->name:" + name + "age:" + age + "Thread:" + Thread.currentThread().getName();
            Log.i(TAG, text);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(OnroadService.this, text, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler();
}
