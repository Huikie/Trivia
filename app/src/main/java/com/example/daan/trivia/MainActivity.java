package com.example.daan.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Function that directs a user from the begin screen to the screen where he/she can play.
    public void startGame(View view){
        Intent intent = new Intent(MainActivity.this, GamePlay.class);
        TextView name = findViewById(R.id.name);
        String name_string = name.getText().toString();

        // Pass along the name that the user typed in when directing to GamePlay.
        intent.putExtra("name", name_string);
        startActivity(intent);
    }
}
