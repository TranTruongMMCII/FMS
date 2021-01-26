package com.example.fms_android.model;

public class Answer {
    long classID;
    int moduleID, questionID, value;
    String traineeID, comment;

    public Answer(long classID, int moduleID, int questionID, int value, String traineeID, String comment) {
        this.classID = classID;
        this.moduleID = moduleID;
        this.questionID = questionID;
        this.value = value;
        this.traineeID = traineeID;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getClassID() {
        return classID;
    }

    public void setClassID(long classID) {
        this.classID = classID;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getTraineeID() {
        return traineeID;
    }

    public void setTraineeID(String traineeID) {
        this.traineeID = traineeID;
    }
}
