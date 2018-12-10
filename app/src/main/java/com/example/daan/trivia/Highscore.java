package com.example.daan.trivia;

import java.io.Serializable;

public class Highscore implements Serializable {
    private String id, score;

    public Highscore(String id, String score) {
        this.id = id;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
