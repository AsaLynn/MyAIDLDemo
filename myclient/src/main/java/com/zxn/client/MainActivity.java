package com.zxn.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zxn.service.IOnroad;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
           // IOnroad.Stub binder =
            IOnroad onroad = IOnroad.Stub.asInterface(service);
            try {
//                onroad.sayHello();
                onroad.sayHello("哈喽",100);
            } catch (RemoteException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "有异常的情况!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setAction("com.lypeer.aidl");
        intent.setPackage("com.zxn.service");
        bindService(intent,connection,BIND_AUTO_CREATE);
    }
}
