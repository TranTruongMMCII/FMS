package com.example.fms_android.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Topic implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long topicID;
    private String topicName;
    private ArrayList<Question> questions;

    public Topic(Long topicID, String topicName, ArrayList<Question> questions) {
        this.topicID = topicID;
        this.topicName = topicName;
        this.questions = questions;
    }

    public Topic(String topicName) {
        this.topicName = topicName;
    }

    public Long getTopicID() {
        return topicID;
    }

    public void setTopicID(Long topicID) {
        this.topicID = topicID;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

}
