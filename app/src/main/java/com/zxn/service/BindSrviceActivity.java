package com.zxn.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zxn.service.service.LocalService;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

@ContentView(R.layout.activity_bind_srvice)
public class BindSrviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(value = {R.id.btn_service})
    private void onViewOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_service:
                //Toast.makeText(this, "onclick", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LocalService.class);
                //通常应该是 BIND_AUTO_CREATE，以便创建尚未激活的服务
                bindService(intent, new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        //绑定成功
                        LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
                        binder.showServiceMsg();
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                }, Context.BIND_AUTO_CREATE);
                break;
        }
    }


}
