package com.example.fms_android.model;

import java.util.Date;

public class    Module {
    private long ModuleID;
    private String admin_module;
    private String ModuleName;
    private Date StartTime;
    private Date EndTime;
    private boolean IsDeleted;
    private Date FeedbackStartTime;
    private Date FeedbackEndTime;
    private Long feedback_module;

    public long getModuleID() {
        return ModuleID;
    }

    public void setModuleID(long moduleID) {
        ModuleID = moduleID;
    }

    public String getAdmin_module() {
        return admin_module;
    }

    public void setAdmin_module(String admin_module) {
        this.admin_module = admin_module;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        StartTime = startTime;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }

    public Date getFeedbackStartTime() {
        return FeedbackStartTime;
    }

    public void setFeedbackStartTime(Date feedbackStartTime) {
        FeedbackStartTime = feedbackStartTime;
    }

    public Date getFeedbackEndTime() {
        return FeedbackEndTime;
    }

    public void setFeedbackEndTime(Date feedbackEndTime) {
        FeedbackEndTime = feedbackEndTime;
    }

    public Long getFeedback_module() {
        return feedback_module;
    }

    public void setFeedback_module(Long feedback_module) {
        this.feedback_module = feedback_module;
    }

    public Module() {
    }

    public Module(long moduleID, String admin_module, String moduleName,
                  Date startTime, Date endTime, boolean isDeleted,
                  Date feedbackStartTime, Date feedbackEndTime, Long feedback_module) {
        ModuleID = moduleID;
        this.admin_module = admin_module;
        ModuleName = moduleName;
        StartTime = startTime;
        EndTime = endTime;
        IsDeleted = isDeleted;
        FeedbackStartTime = feedbackStartTime;
        FeedbackEndTime = feedbackEndTime;
        this.feedback_module = feedback_module;
    }
}
