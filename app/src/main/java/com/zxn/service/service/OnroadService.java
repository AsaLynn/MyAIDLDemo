package com.zxn.service.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.zxn.service.IOnroad;

public class OnroadService extends Service {

    public OnroadService() {
    }

    private String TAG = "OnroadService";
    private IOnroad.Stub binder = new IOnroad.Stub() {

        @Override
        public void sayHello(String name, int age) throws RemoteException {
            String text = "sayHello: --->name" + name + "age:" + age;
            Log.i(TAG, text);
            //Toast.makeText(OnroadService.this, text, Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
