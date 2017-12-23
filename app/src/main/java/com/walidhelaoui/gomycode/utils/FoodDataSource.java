package com.walidhelaoui.gomycode.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.walidhelaoui.gomycode.Entity.Food;
import com.walidhelaoui.gomycode.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by walid on 23/12/2017.
 */

public class FoodDataSource {


    public static List<Food> foods = new ArrayList<>();

    public static void setFood(final Context context) {
        foods = new ArrayList<>();

        final String ServerURL = LoginActivity.ServerAddress+"gomycode/food.json";
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
                                Food food = new Food();
                                food.setName(jsonArray.getJSONObject(i).getString("name"));
                                food.setCity(jsonArray.getJSONObject(i).getString("city"));
                                food.setPrice(jsonArray.getJSONObject(i).getLong("price"));
                                food.setType(jsonArray.getJSONObject(i).getString("type"));
                                foods.add(food);
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
