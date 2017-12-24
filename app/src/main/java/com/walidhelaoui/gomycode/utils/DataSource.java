package com.walidhelaoui.gomycode.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.walidhelaoui.gomycode.Entity.Data;
import com.walidhelaoui.gomycode.Entity.Food;
import com.walidhelaoui.gomycode.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walid on 24/12/2017.
 */

public class DataSource {

    public static List<Data> datas = new ArrayList<>();

    public static void setData(final Context context) {
        datas = new ArrayList<>();

        final String ServerURL = LoginActivity.ServerAddress+"gomycode/data.json";
        final String TAG = "FoodDataSource";
        String REQUEST_TAG = "com.androidtutorialpoint.volleyJsonObjectRequest";
        StringRequest postRequest = new StringRequest(Request.Method.GET, ServerURL, // the request body, which is a JsonObject otherwise null
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);

                            for (int i = 0; i < jsonArray.length() ; i++) {
                                Log.e(TAG,String.valueOf(i));
                                Data data = new Data();
                                data.setName(jsonArray.getJSONObject(i).getString("name"));
                                data.setCity(jsonArray.getJSONObject(i).getString("city"));
                                data.setPrice(jsonArray.getJSONObject(i).getLong("price"));
                                data.setType(jsonArray.getJSONObject(i).getString("type"));
                                data.setPlace(jsonArray.getJSONObject(i).getString("place"));
                                datas.add(data);
                            }
                            Log.e(TAG,jsonArray.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG,"error");
                    }
                }
        ) {




        };
        // Adding JsonObject request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(postRequest, REQUEST_TAG);

    }

}
