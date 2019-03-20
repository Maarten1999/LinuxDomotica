package com.mpapps.myapplication.Volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mpapps.myapplication.Models.LightModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VolleyService
{
    private static VolleyService sInstance = null;
    private RequestQueue requestQueue;
    private VolleyListener listener;

    private VolleyService(Context context, VolleyListener listener){
        requestQueue = Volley.newRequestQueue(context);
        this.listener = listener;
    }

    public static VolleyService getInstance(Context ctx, VolleyListener volleyListener){
        if(sInstance == null){
            sInstance = new VolleyService(ctx, volleyListener);
        }
        return sInstance;
    }

    public void getLightsRequest(String url){
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,url,null,
                response ->
                {
                    listener.getLightsReceived(parseJsonArray(response));
                },
                error -> {
                    listener.getLightsError();
                }
        );

        requestQueue.add(request);
    }

    public void changeLight(String url){
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT, url, null,
                response -> listener.changeLightSuccess(),
                error -> listener.changeLightError()
        );

        requestQueue.add(request);
    }

    private static List<LightModel> parseJsonArray(JSONArray response){
        List<LightModel> lightModels = new ArrayList<>();

        try{
            for (int i = 0; i < response.length(); i++) {
                JSONObject lightJson = response.getJSONObject(i);
                int id = lightJson.getInt("id");
                String name = lightJson.getString("name");
                String color = lightJson.getString("color");
                boolean state = lightJson.getBoolean("on");

                LightModel lightModel = new LightModel(id, name, color, state);
                lightModels.add(lightModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lightModels;
    }
}
