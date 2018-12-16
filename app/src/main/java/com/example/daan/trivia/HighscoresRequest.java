package com.example.daan.trivia;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HighscoresRequest implements Response.Listener<JSONArray>, Response.ErrorListener {
    Context context;
    Callback callback_activity;
    ArrayList<Highscore> highscores = new ArrayList<>();

    /**Method that makes a callback possible.*/
    public interface Callback {
        void gotHighscores(ArrayList<Highscore> highscores);
        void gotHighscoresError(String message);
    }

    // Constructor.
    public HighscoresRequest(Context context) {
        this.context = context;
    }

    /**Method that gets the highscores from the API.*/
    public void getHighscores(Callback activity){
        callback_activity = activity;
        String json_url = "https://ide50-huikie.cs50.io:8080/list";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(json_url,this,this);
        queue.add(jsonArrayRequest);
    }

    @Override
    /**The highscores aren't received successfully, an error message will be displayed.*/
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotHighscoresError(error.getMessage());
    }

    @Override
    /**The highscores are received successfully and will be added to the highscores ArrayList.*/
    public void onResponse(JSONArray response) {
        try{
            for (int i = 0; i < response.length(); i++){
                JSONObject highscore_info = response.getJSONObject(i);
                String name = highscore_info.getString("name");
                String score = highscore_info.getString("score");
                highscores.add(new Highscore(name, score));

            }
            callback_activity.gotHighscores(highscores);
        }
        catch(JSONException e){
            System.out.println(e.toString());
        }
    }

}
