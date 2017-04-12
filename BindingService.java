package com.example.mido.servicetutorials;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

/**
 * Created by Mina on 4/12/2017.
 */

public class BindingService extends Service {

    private  int mRandomBNumber;
    private boolean mIsRandomGeneratorOn;

    private int MIN=0;
    private int MAX=100;

class MyServiceBinder extends Binder //or imlements IBinder
{
    public BindingService getService()
    {
        return BindingService.this;
    }
}
private IBinder mBinder=new MyServiceBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("ServiceDemo","In onBind");
        return mBinder;
    }
    @Override
    public void onRebind(Intent intent) {
        Log.d("ServiceDemo","In onRebind");
        super.onRebind(intent);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("ServiceDemo","In onStartCommend");
        mIsRandomGeneratorOn=true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                startGenerator();
            }
        }).start();
        return START_STICKY;
    }

    private void startGenerator(){

    while (mIsRandomGeneratorOn) {
    try{
    Thread.sleep(1000);
    if(mIsRandomGeneratorOn)
    {
       mRandomBNumber=new Random().nextInt(MAX)+MIN;
        Log.d("ServiceDemo","Thread ID is :" + Thread.currentThread().getId()+", Random num is: "+ mRandomBNumber);

    }
} catch (InterruptedException e) {
    Log.d("ServiceDemo","thread interrupted");
}

    }
}

    private void stopGenerator(){mIsRandomGeneratorOn=false;}

    @Override
    public void onDestroy() {
        Log.d("ServiceDemo","In onDestroy");

        stopGenerator();
        super.onDestroy();
    }
}
