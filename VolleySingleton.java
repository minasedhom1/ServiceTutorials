package com.example.mido.servicetutorials;

/**
 * Created by mido on 3/8/2017.
 */
import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton extends Application {

    public static final String TAG = VolleySingleton.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private static VolleySingleton mInstance;  //1-  private static only object of the class.

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }

    public static synchronized VolleySingleton getInstance() {
        //  if(mInstance!=null)
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}