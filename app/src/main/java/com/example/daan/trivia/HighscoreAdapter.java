package com.example.daan.trivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HighscoreAdapter extends ArrayAdapter<Highscore> {
    ArrayList<Highscore> highscore;

    //Constructor
    public HighscoreAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Highscore> objects) {
        super(context, resource, objects);
        highscore = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.highscore, parent, false);
        }
        int index = position;
        TextView name = convertView.findViewById(R.id.id);
        TextView score = convertView.findViewById(R.id.score);
//        ImageView image = convertView.findViewById(R.id.food_Image);
//        TextView food_title = convertView.findViewById(R.id.food);
//        TextView food_price = convertView.findViewById(R.id.price);
//        String price = menuItem.get(index).getPrice();
//        String name = menuItem.get(index).getName();

        // I used Picasso to get the images from the web and put them in an ImageView (http://square.github.io/picasso/)
//        String image_link = menuItem.get(index).getImageUrl();
//        Picasso.get().load(image_link).into(image);
//
//        food_title.setText(name);
//        food_price.setText(price);
        name.setText(highscore.get(index).getName());
        score.setText(highscore.get(index).getScore());
        return convertView;
    }
}
