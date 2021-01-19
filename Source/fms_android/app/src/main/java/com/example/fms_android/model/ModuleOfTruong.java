package com.example.fms_android.model;

import java.util.Date;

public class ModuleOfTruong {
    int moduleID, feedbackID;
    String adminID, moduleName;
    Date startTime, endTime, feedbackStartTime, feedbackEndTime;
    boolean isDeleted;

    public ModuleOfTruong(int moduleID, int feedbackID, String adminID, String moduleName, Date startTime,
                          Date endTime, Date feedbackStartTime, Date feedbackEndTime, boolean isDeleted) {
        this.moduleID = moduleID;
        this.feedbackID = feedbackID;
        this.adminID = adminID;
        this.moduleName = moduleName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.feedbackStartTime = feedbackStartTime;
        this.feedbackEndTime = feedbackEndTime;
        this.isDeleted = isDeleted;
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
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

    public Date getFeedbackStartTime() {
        return feedbackStartTime;
    }

    public void setFeedbackStartTime(Date feedbackStartTime) {
        this.feedbackStartTime = feedbackStartTime;
    }

    public Date getFeedbackEndTime() {
        return feedbackEndTime;
    }

    public void setFeedbackEndTime(Date feedbackEndTime) {
        this.feedbackEndTime = feedbackEndTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
