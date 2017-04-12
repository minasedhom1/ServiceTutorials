package com.example.mido.servicetutorials;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mido on 4/3/2017.
 */

public class DownloadStateReceiver extends BroadcastReceiver {
    // Prevents instantiation
    protected DownloadStateReceiver() {
    }
    // Called when the BroadcastReceiver gets an Intent it's registered to receive
    @Override
    public void onReceive(Context context, Intent intent) {
/*
        Log.d("onReceive",intent.getStringExtra(Constants.EXTENDED_DATA_STATUS));
        MainActivity.textView.setText(intent.getStringExtra(Constants.EXTENDED_DATA_STATUS));*/
    }
}
