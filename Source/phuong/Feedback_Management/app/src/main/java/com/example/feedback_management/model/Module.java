package com.example.feedback_management.model;

import java.util.Date;

public class Module {
    private Long moduleID;
    private Date feedbackStartTime;
    private Date feedbackEndTime;

    public Module(Long moduleID, Date feedbackStartTime, Date feedbackEndTime) {
        this.moduleID = moduleID;
        this.feedbackStartTime = feedbackStartTime;
        this.feedbackEndTime = feedbackEndTime;
    }

    public Long getModuleID() {
        return moduleID;
    }

    public void setModuleID(Long moduleID) {
        this.moduleID = moduleID;
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
}
