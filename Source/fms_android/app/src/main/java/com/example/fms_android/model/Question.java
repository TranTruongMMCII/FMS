package com.example.fms_android.model;

import java.io.Serializable;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long questionID;
    private String quesionContent;
    private boolean isDeleted;

    public Question(Long questionID, String quesionContent, boolean isDeleted) {
        this.questionID = questionID;
        this.quesionContent = quesionContent;
        this.isDeleted = isDeleted;
    }

    public Question(String questionContent, boolean isDeleted) {
        this.quesionContent = questionContent;
        this.isDeleted = isDeleted;
    }

    public Long getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Long questionID) {
        this.questionID = questionID;
    }

    public String getQuesionContent() {
        return quesionContent;
    }

    public void setQuesionContent(String quesionContent) {
        this.quesionContent = quesionContent;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

}
