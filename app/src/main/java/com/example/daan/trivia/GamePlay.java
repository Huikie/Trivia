package com.example.daan.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

// Implements QuestionRequest.Callback to be able to do a callback to the QuestionRequest class to get the question from the API.
public class GamePlay extends AppCompatActivity implements QuestionRequest.Callback, HighscoresPostRequest.Callback{

    @Override
    public void gotpostHighscores(String highscores) {
    }

    @Override
    public void gotpostHighscoresError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    ArrayList<Question> questions_array;
    int i = 0;
    int score;
    String answer;
    String correct_answer;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        QuestionRequest x = new QuestionRequest(this);
        x.getQuestions(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        user_name = name;
    }

    @Override
    /**The questions are received correctly and the question TetView is set to the first question.*/
    public void gotQuestions(ArrayList<Question> questions) {
        questions_array = questions;
        TextView question = findViewById(R.id.question);
        question.setText(Html.fromHtml(questions_array.get(0).getQuestion()));
    }

    @Override
    /**The questions aren't received, so an error message will be displayed.*/
    public void gotQuestionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**The user of the app can answer the questions and will receive a score based on their answers.*/
    public void givenAnswer(View view) {

        TextView question = findViewById(R.id.question);
        TextView end_score = findViewById(R.id.endScore);

        Button False = findViewById(R.id.buttonFalse);
        Button True = findViewById(R.id.buttonTrue);

        if (i == 0) {

            // Define the correct answer for the first question.
            correct_answer = questions_array.get(i).getCorrect_answer();

            i += 1;
        }

        if (i > 0) {

            // Set the question TextView equal to the question from the API and make the Html display.
            question.setText(Html.fromHtml(questions_array.get(i).getQuestion()));

            // Define the correct answer for the current question.
            correct_answer = questions_array.get(i).getCorrect_answer();

            i += 1;
        }

            // The user's answer is retrieved.
            if (view.getId() == R.id.buttonFalse) {
                answer = "False";
            }
            if (view.getId() == R.id.buttonTrue) {
                answer = "True";
            }

            // The correct answer is compared with the user's answer and a score is defined.
            if (answer.equals(correct_answer)) {
                score += 1;
            }

            // If all questions have been answered the user will see it's score and the score and the user's name will be posted into the leaderboard.
            if (i == questions_array.size()){
                question.setText("");
                False.setVisibility(View.INVISIBLE);
                True.setVisibility(View.INVISIBLE);
                String newLine = System.getProperty("line.separator");
                end_score.setText("- You answered " + questions_array.size() + " questions"+ newLine +"- Your score = " + score + " / " + questions_array.size());

                HighscoresPostRequest x2 = new HighscoresPostRequest(this);
                x2.postHighscores(this,""+score, ""+user_name);
            }

        }

        public void toHighscore(View view){
            Intent intent = new Intent(GamePlay.this, HighscoresActivity.class);
            startActivity(intent);
        }

    }
