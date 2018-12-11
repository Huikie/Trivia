package com.example.daan.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HighscoresPostRequest implements Response.Listener<String>, Response.ErrorListener{
    Context context;
    Callback callback_activity;
    String new_score;
    String new_user;
    public class PostRequest extends StringRequest {

        // Constructor
        public PostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        // Method to supply parameters to the request
        @Override
        protected Map<String, String> getParams() {

            Map<String, String> params = new HashMap<>();
            params.put("score", new_score);
            params.put("name", new_user);
            return params;
        }
    }

    public interface Callback {
        void gotpostHighscores(String highscores);
        void gotpostHighscoresError(String message);
    }

    public HighscoresPostRequest(Context context) {
        this.context = context;
    }

    public void postHighscores(Callback activity, String score, String user_name){
        callback_activity = activity;
        new_score = score;
        new_user = user_name;
        String json_url = "https://ide50-huikie.cs50.io:8080/list";
        RequestQueue queue = Volley.newRequestQueue(context);

//        Map<String, String> params = new HashMap();
//        params.put("score", score);
//        JSONObject parameters = new JSONObject(params);
        //Log.d("parameters", parameters.toString());
        //JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, json_url, parameters, this,this);
        PostRequest postRequest = new PostRequest(Request.Method.POST, json_url,this,this);
        queue.add(postRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotpostHighscoresError(error.getMessage());
    }

    @Override
    public void onResponse(String response) {
        try{
            callback_activity.gotpostHighscores(response);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }

    }
}
