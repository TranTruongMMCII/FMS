package com.example.fms_android.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.fms_android.model.Admin;

import java.io.Serializable;
import java.util.List;

public class Feedback implements Parcelable {
    private Long feedbackID;
    private String title;
    private Admin adminID;
    private boolean isdeleted;
    private List<Feedback_Question> feedback_Questions;
    private TypeFeedback feedback_typeID;

    public Feedback(Long FeedbackID, String title, Admin adminID, boolean isdeleted, List<Feedback_Question> feedback_Questions, TypeFeedback feedback_typeID) {
        this.feedbackID = FeedbackID;
        this.title = title;
        this.adminID = adminID;
        this.isdeleted = isdeleted;
        this.feedback_Questions = feedback_Questions;
        this.feedback_typeID = feedback_typeID;
    }

    protected Feedback(Parcel in) {
        if (in.readByte() == 0) {
            feedbackID = null;
        } else {
            feedbackID = in.readLong();
        }
        title = in.readString();
        isdeleted = in.readByte() != 0;
    }

    public static final Creator<Feedback> CREATOR = new Creator<Feedback>() {
        @Override
        public Feedback createFromParcel(Parcel in) {
            return new Feedback(in);
        }

        @Override
        public Feedback[] newArray(int size) {
            return new Feedback[size];
        }
    };

    public Long getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(Long FeedbackID) {
        this.feedbackID = FeedbackID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
    }

    public Admin getAdminID() {
        return adminID;
    }

    public void setAdminID(Admin adminID) {
        this.adminID = adminID;
    }

    public List<Feedback_Question> getFeedback_Questions() {
        return feedback_Questions;
    }

    public void setFeedback_Questions(List<Feedback_Question> feedback_Questions) {
        this.feedback_Questions = feedback_Questions;
    }

    public TypeFeedback getFeedback_typeID() {
        return feedback_typeID;
    }

    public void setFeedback_typeID(TypeFeedback feedback_typeID) {
        this.feedback_typeID = feedback_typeID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (feedbackID == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(feedbackID);
        }
        dest.writeString(title);
        dest.writeByte((byte) (isdeleted ? 1 : 0));
    }
}
