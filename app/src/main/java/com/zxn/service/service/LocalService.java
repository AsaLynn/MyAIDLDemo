package com.zxn.service.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Random;

public class LocalService extends Service {

    private LocalBinder localBinder = new LocalBinder();
    private Random random = new Random();

    public LocalService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class LocalBinder extends Binder {

        public LocalBinder() {
            super();
        }

        public void showServiceMsg(){
            show();
        }
    }

    public int getRandomNum() {
        return random.nextInt(100);
    }

    public void show(){
        Toast
                .makeText(this, "this is a message from service!", Toast.LENGTH_SHORT).show();
    }

}
