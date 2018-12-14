package com.example.daan.trivia;

import java.io.Serializable;

public class Highscore implements Serializable, Comparable<Highscore> {

    private String name, score;

    public Highscore(String name, String score) {
        this.name = name;
        this.score = score;
    }

    // This method defines a way to sort a list from highscores to lower scores. This method makes it possible to use Collections.sort() to sort the higscoreslist in the HighscoresActivity.
    // I found this way of sorting a list in the following video: https://www.youtube.com/watch?v=hncd_WgF83c.
    @Override
    public int compareTo(Highscore other) {
        int compareInt = this.score.compareTo(other.score);
        if (compareInt < 0) return 1;
        if (compareInt > 0) return -1;
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
