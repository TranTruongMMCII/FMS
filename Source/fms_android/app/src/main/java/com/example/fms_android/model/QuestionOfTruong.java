package com.example.fms_android.model;

public class QuestionOfTruong {
    private int questionID;
    private int topicID;
    private String questionContent;
    private boolean isDeleted;

    public QuestionOfTruong(int questionID, int topicID, String questionContent, boolean isDeleted) {
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

    public boolean getIsDeleted() {
        return isDeleted;
    }
}
