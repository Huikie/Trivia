package com.example.daan.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HighscoresPostRequest implements Response.Listener<JSONObject>, Response.ErrorListener{
    Context context;
    Callback callback_activity;

    public interface Callback {
        void gotpostHighscores(JSONObject highscores);
        void gotpostHighscoresError(String message);
    }

    public HighscoresPostRequest(Context context) {
        this.context = context;
    }

    public void postHighscores(Callback activity, String score){
        callback_activity = activity;
        String json_url = "https://ide50-huikie.cs50.io:8080/list";
        RequestQueue queue = Volley.newRequestQueue(context);

        Map<String, String> params = new HashMap();
        params.put("score", score);
        JSONObject parameters = new JSONObject(params);
        Log.d("parameters", parameters.toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, json_url, parameters, this,this);
        queue.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotpostHighscoresError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try{
            callback_activity.gotpostHighscores(response);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }

    }
}
