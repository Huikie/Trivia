package com.example.daan.trivia;

import java.io.Serializable;

public class Question implements Serializable {
    private String question, correct_answer;

    public Question(String question, String correct_answer){
        this.question = question;
        this.correct_answer = correct_answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }
}
