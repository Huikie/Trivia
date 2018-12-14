package com.example.daan.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class HighscoresActivity extends AppCompatActivity implements HighscoresRequest.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        HighscoresRequest x = new HighscoresRequest(this);
        x.getHighscores(this);
        //Toast.makeText(this, "Started", Toast.LENGTH_LONG).show();
}

    @Override
    public void gotHighscores(ArrayList<Highscore> highscores) {
        Collections.sort(highscores);
        Toast.makeText(this, highscores.toString(), Toast.LENGTH_LONG).show();
        HighscoreAdapter itemsAdapter = new HighscoreAdapter(this, R.layout.highscore, highscores);
        ListView highscore_list = findViewById(R.id.highScores);
        highscore_list.setAdapter(itemsAdapter);
    }

    @Override
    public void gotHighscoresError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }
}
