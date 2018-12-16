package com.example.daan.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

// Implements HighscoresRequest.Callback to be able get the highscores from the API via a callback.
public class HighscoresActivity extends AppCompatActivity implements HighscoresRequest.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        HighscoresRequest x = new HighscoresRequest(this);
        x.getHighscores(this);
}

    @Override
    /**The highscores are retrieved successfully, are sorted and are
     * put nicely in a ListView using a custom adapter*/
    public void gotHighscores(ArrayList<Highscore> highscores) {

        // Sort the highscores from big to small.
        Collections.sort(highscores);

        HighscoreAdapter itemsAdapter = new HighscoreAdapter(this, R.layout.highscore, highscores);
        ListView highscore_list = findViewById(R.id.highScores);
        highscore_list.setAdapter(itemsAdapter);
    }

    @Override
    /**The highscores aren't retrieved successfully, an error message will be shown.*/
    public void gotHighscoresError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
