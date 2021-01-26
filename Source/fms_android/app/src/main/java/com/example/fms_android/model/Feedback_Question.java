package com.example.fms_android.model;

public class Feedback_Question {
    private Feedback_QuestionKey feedback_QuestionKey;

    public Feedback_Question(Feedback_QuestionKey feedback_QuestionKey) {
        this.feedback_QuestionKey = feedback_QuestionKey;
    }

    public Feedback_QuestionKey getFeedback_QuestionKey() {
        return feedback_QuestionKey;
    }

    public void setFeedback_QuestionKey(Feedback_QuestionKey feedback_QuestionKey) {
        this.feedback_QuestionKey = feedback_QuestionKey;
    }
}
