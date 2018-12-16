package com.example.daan.trivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HighscoreAdapter extends ArrayAdapter<Highscore> {
    ArrayList<Highscore> highscore;

    //Constructor
    public HighscoreAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Highscore> objects) {
        super(context, resource, objects);
        highscore = objects;
    }
    @NonNull
    @Override
    /**Custom view adapter that can be used to get the user's score and name nicely in a ListView*/
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.highscore, parent, false);
        }
        int index = position;
        TextView name = convertView.findViewById(R.id.id);
        TextView score = convertView.findViewById(R.id.score);

        name.setText(highscore.get(index).getName());
        score.setText(highscore.get(index).getScore());
        return convertView;
    }
}
