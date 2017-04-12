package com.example.mido.servicetutorials;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by mido on 4/3/2017.
 */

public class BackGroundService extends IntentService {
    public BackGroundService() {
        super("HI SERVICE");
    }
ArrayList<Item> items;
    @Override
    protected void onHandleIntent(Intent intent) {
        // Gets data from the incoming Intent
        //String dataString = workIntent.getDataString();
            Log.d("INTENT_DATA",intent.getData().toString());
        // Do work here, based on the contents of dataString
           /*
     * Creates a new Intent containing a Uri object
     * BROADCAST_ACTION is a custom Intent action
     */

     items=new ArrayList<>();
        final StringRequest request1=new StringRequest(Request.Method.POST,
                "http://sodicservice.azurewebsites.net/Sodic/Item?itemID=96",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                           response.toString();
try{
                        JsonElement root=new JsonParser().parse(response);
                        response = root.getAsString();
                        JSONObject object=new JSONObject(response);

                        Item item=new Item();
                        item.setId(object.getString("ItemID"));
                        item.setName(object.getString("Name_En"));
                        item.setDescription(object.getString("Description_En"));
                        items.add(item);
                        }catch (JSONException e) {e.printStackTrace();}

                        Intent localIntent =
                                new Intent(Constants.BROADCAST_ACTION)
                                        // Puts the status into the Intent
                                        .putExtra(Constants.EXTENDED_DATA_STATUS, items);
                        // Broadcasts the Intent to receivers in this app.
                        LocalBroadcastManager.getInstance(getApplication()).sendBroadcast(localIntent);


                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
      Log.d("VOLLEY_ERROR",error.getMessage());
            }});


        ScheduledExecutorService scheduleTaskExecutor = Executors.newScheduledThreadPool(5);

        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                VolleySingleton.getInstance().addToRequestQueue(request1);

            }
        }, 0, 10, TimeUnit.SECONDS);

    }
}
