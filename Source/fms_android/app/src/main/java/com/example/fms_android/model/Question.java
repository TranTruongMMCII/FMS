package com.example.fms_android.model;

public class Question {
    private Long questionID;
    private Long topicID;
    private String nameTopic;
    private String questionContent;


    public Question(String nameTopic) {
        this.questionID = questionID;
        this.topicID = topicID;
        this.nameTopic = nameTopic;
        this.questionContent = questionContent;
    }


    public Long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

    public Long getTopicID() {
        return topicID;
    }

    public void setTopicID(Long topicID) {
        this.topicID = topicID;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }












}
