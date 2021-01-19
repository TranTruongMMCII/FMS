package com.example.fms_android.model;

public class QuestionOfTruong {
    private int questionID;
    private int topicID;
    private String questionContent;
    private int isDeleted;

    public QuestionOfTruong(int questionID, int topicID, String questionContent, int isDeleted) {
        this.questionID = questionID;
        this.topicID = topicID;
        this.questionContent = questionContent;
        this.isDeleted = isDeleted;
    }

    public int getQuestionID() {
        return questionID;
    }

    public int getTopicID() {
        return topicID;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public int getIsDeleted() {
        return isDeleted;
    }
}
