package com.example.seu;

import java.io.Serializable;

public class Questionnaire implements Serializable {

    private String question, answer;

    public Questionnaire(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Questionnaire() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
