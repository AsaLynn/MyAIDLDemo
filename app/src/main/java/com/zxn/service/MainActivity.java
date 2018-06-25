package com.zxn.service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(value = {R.id.btn_service,R.id.btn_messager})
    private void onViewOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_service:
                startActivity(new Intent(this,BindSrviceActivity.class));
                break;
            case R.id.btn_messager:
                startActivity(new Intent(this,MessagerActivity.class));
                break;
        }
    }
}
