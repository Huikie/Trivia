package com.example.daan.trivia;

import java.io.Serializable;

public class Question implements Serializable {
    private String question, correct_answer;

    // Constructor
    public Question(String question, String correct_answer){
        this.question = question;
        this.correct_answer = correct_answer;
    }

    // Getters & setters.
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

}
