package com.example.guanWang.model;

import java.io.Serializable;

public class QuestionHot implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "QuestionHot{" +
                "id=" + id +
                ", question='" + question + '\'' +
                '}';
    }
}
