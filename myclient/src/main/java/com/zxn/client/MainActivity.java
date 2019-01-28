package com.zxn.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zxn.service.IOnroad;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // IOnroad.Stub binder =
            mOnroad = IOnroad.Stub.asInterface(service);
            try {
//                onroad.sayHello();
                mOnroad.sayHello("哈喽", 100);
            } catch (RemoteException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "有异常的情况!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mOnroad = null;
        }
    };
    private IOnroad mOnroad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service();
        findViewById(R.id.tv_call_service).setOnClickListener(this);
    }

    private void service() {
        Intent intent = new Intent();
        intent.setAction("com.lypeer.aidl");
        intent.setPackage("com.zxn.service");
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        try {
            if (null != mOnroad) {
                mOnroad.sayHello("say onClick", 1028);
            } else {
                service();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            Toast.makeText(this, "调用出现异常情况!", Toast.LENGTH_SHORT).show();
        }
    }
}
