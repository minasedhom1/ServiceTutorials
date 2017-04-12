package com.example.mido.servicetutorials;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
Button start,stop;
/*
    private final Handler handler = new Handler();
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("mainThread", "ID :"+Thread.currentThread().getId());

     start= (Button) findViewById(R.id.start_btn);
        stop= (Button) findViewById(R.id.stop_btn);

           start.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   startService(new Intent(MainActivity.this,BindingService.class));
               }
           });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this,BindingService.class));
            }
        });
}
}
