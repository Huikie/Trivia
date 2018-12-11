package com.example.daan.trivia;

import java.io.Serializable;

public class Highscore implements Serializable, Comparable<Highscore> {

    private String name, score;

    public Highscore(String name, String score) {
        this.name = name;
        this.score = score;
    }

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
