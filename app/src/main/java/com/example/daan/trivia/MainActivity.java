package com.example.daan.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startGame(View view){
        Intent intent = new Intent(MainActivity.this, GamePlay.class);
        TextView name = findViewById(R.id.name);
        String name_string = name.getText().toString();
        intent.putExtra("name", name_string);
        startActivity(intent);
    }
}
