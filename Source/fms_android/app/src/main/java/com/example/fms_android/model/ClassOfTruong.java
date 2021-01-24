package com.example.fms_android.model;

import java.util.Date;

public class ClassOfTruong {

    int classID, capacity;
    String className;
    Date startTime, endTime;
    boolean isDeleted;

    public ClassOfTruong(int classID, int capacity, String className, Date startTime, Date endTime, boolean isDeleted) {
        this.classID = classID;
        this.capacity = capacity;
        this.className = className;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDeleted = isDeleted;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
