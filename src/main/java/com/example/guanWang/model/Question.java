package com.example.guanWang.model;

import java.io.Serializable;

public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String question;

    private String answer;

    private String seriesId;

    private Integer viewCount;

    private String firstLevel;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getFirstLevel() {
        return firstLevel;
    }

    public void setFirstLevel(String firstLevel) {
        this.firstLevel = firstLevel;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", seriesId='" + seriesId + '\'' +
                ", viewCount=" + viewCount +
                ", firstLevel='" + firstLevel + '\'' +
                '}';
    }
}
