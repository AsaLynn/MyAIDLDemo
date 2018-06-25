package com.zxn.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

@ContentView(R.layout.activity_messager)
public class MessagerActivity extends AppCompatActivity {

    static final int MSG_SAY_HELLO = 1;

    Messenger mService = null;
    boolean mBound;

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            //接收onBind()传回来的IBinder，并用它构造Messenger
            mService = new Messenger(service);
            mBound = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            mService = null;
            mBound = false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        //绑定服务端的服务，此处的action是service在Manifests文件里面声明的
        Intent intent = new Intent();
        intent.setAction("com.lypeer.messenger");
        //不要忘记了包名，不写会报错
        intent.setPackage("com.zxn.service");
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    @Event(value = {R.id.btn_send})
    private void onViewOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                sayHello();
                break;
        }
    }

    //调用此方法时会发送信息给服务端
    public void sayHello() {
        if (!mBound) return;
        //发送一条信息给服务端
        Message msg = Message.obtain(null, MSG_SAY_HELLO, 0, 0);
        try {
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
