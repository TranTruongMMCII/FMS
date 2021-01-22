package com.example.fms_android.model;

public class Answer {
    int classID, moduleID, questionID, value;
    String traineeID;

    public Answer(int classID, int moduleID, int questionID, int value, String traineeID) {
        this.classID = classID;
        this.moduleID = moduleID;
        this.questionID = questionID;
        this.value = value;
        this.traineeID = traineeID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
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