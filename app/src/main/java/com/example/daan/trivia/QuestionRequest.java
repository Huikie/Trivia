package com.example.daan.trivia;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    Context context;
    Callback callback_activity;
    ArrayList<Question> questions = new ArrayList<>();

    public interface Callback {
        void gotQuestions(ArrayList<Question> questions);
        void gotQuestionsError(String message);
    }
    public QuestionRequest(Context context){
        this.context = context;
    }

    public void getQuestions(Callback activity){
        callback_activity = activity;
        String json_url = "https://opentdb.com/api.php?amount=10&type=boolean";
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(json_url, null, this,this);
        queue.add(jsonObjectRequest);
    }
    @Override
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotQuestionsError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try{

            JSONArray jsonArray = response.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject question_info = jsonArray.getJSONObject(i);
                String question = question_info.getString("question");
                String correct_answer = question_info.getString("correct_answer");
                questions.add(new Question(question, correct_answer));

            }
            callback_activity.gotQuestions(questions);
        }
        catch(JSONException e){
            System.out.println(e.toString());
        }
    }
}

