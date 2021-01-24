package com.example.fms_android.model;

public class FeedbackOfTruong {
        String feedbackTitle;
        String classID;
        String className;
        String moduleID;
        String moduleName;
        String endTime;
        String status;

public String getFeedbackTitle() {
        return feedbackTitle;
        }

public String getClassID() {
        return classID;
        }

public String getClassName() {
        return className;
        }

public String getModuleID() {
        return moduleID;
        }

public String getModuleName() {
        return moduleName;
        }

public String getEndTime() {
        return endTime;
        }

public String getStatus() {
        return status;
        }

        public void setFeedbackTitle(String feedbackTitle) {
                this.feedbackTitle = feedbackTitle;
        }

        public void setClassID(String classID) {
                this.classID = classID;
        }

        public void setClassName(String className) {
                this.className = className;
        }

        public void setModuleID(String moduleID) {
                this.moduleID = moduleID;
        }

        public void setModuleName(String moduleName) {
                this.moduleName = moduleName;
        }

        public void setEndTime(String endTime) {
                this.endTime = endTime;
        }

        public void setStatus(String status) {
                this.status = status;
        }

        public FeedbackOfTruong(String feedbackTitle, String classID, String className, String moduleID, String moduleName, String endTime, String status) {
        this.feedbackTitle = feedbackTitle;
        this.classID = classID;
        this.className = className;
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.endTime = endTime;
        this.status = status;


        }
}