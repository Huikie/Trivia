package com.example.daan.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GamePlay extends AppCompatActivity implements QuestionRequest.Callback {
    ArrayList<Question> questions_array;
    //int size = questions_array.size();
    int i = 0;
    int score;
    String answer;
    String correct_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        QuestionRequest x = new QuestionRequest(this);
        x.getQuestions(this);
        //Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void gotQuestions(ArrayList<Question> questions) {
        questions_array = questions;
        TextView question = findViewById(R.id.question);
        question.setText(questions_array.get(0).getQuestion());
        //i = i + 1;
    }


    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    public void givenAnswer(View view) {
        Log.d("score", ""+score);
        TextView question = findViewById(R.id.question);
        TextView end_score = findViewById(R.id.endScore);
        Button False = findViewById(R.id.buttonFalse);
        Button True = findViewById(R.id.buttonTrue);
        if (i == 0) {
            correct_answer = questions_array.get(i).getCorrect_answer();
            i += 1;
        }
        if (i > 0) {
            question.setText(questions_array.get(i).getQuestion());
            correct_answer = questions_array.get(i).getCorrect_answer();
            i += 1;
        }
            if (view.getId() == R.id.buttonFalse) {
                answer = "False";
            }
            if (view.getId() == R.id.buttonTrue) {
                answer = "True";
            }
            if (answer.equals(correct_answer)) {
                score += 1;
            }
            if (i == questions_array.size()){
                question.setText("");
                False.setVisibility(View.INVISIBLE);
                True.setVisibility(View.INVISIBLE);
                end_score.setText("You answered " + questions_array.size() + " questions. Your score = " + score + " / " + questions_array.size());
            }
        }
    }