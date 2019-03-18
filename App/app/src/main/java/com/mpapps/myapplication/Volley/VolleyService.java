package com.mpapps.myapplication.Volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mpapps.myapplication.LightModel;

import org.json.JSONArray;
import org.json.JSONException;

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

    public static VolleyService getsInstance(Context ctx, VolleyListener volleyListener){
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
    }

    private static List<LightModel> parseJsonArray(JSONArray response){
        List<LightModel> lightModels = new ArrayList<>();

        try{
            for (int i = 0; i < response.length(); i++) {
                int id = response.getJSONObject(i).getInt("id");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lightModels;
    }
}
