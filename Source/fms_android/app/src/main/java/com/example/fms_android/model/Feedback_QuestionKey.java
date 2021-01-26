package com.example.fms_android.model;

public class Feedback_QuestionKey {
    private Feedback feedback_feedback_question;
    private Question question_feedback_question;

    public Feedback_QuestionKey(Feedback feedback_feedback_question, Question question_feedback_question) {
        this.feedback_feedback_question = feedback_feedback_question;
        this.question_feedback_question = question_feedback_question;
    }

    public Feedback getFeedback_feedback_question() {
        return feedback_feedback_question;
    }

    public void setFeedback_feedback_question(Feedback feedback_feedback_question) {
        this.feedback_feedback_question = feedback_feedback_question;
    }

    public Question getQuestion_feedback_question() {
        return question_feedback_question;
    }

    public void setQuestion_feedback_question(Question question_feedback_question) {
        this.question_feedback_question = question_feedback_question;
    }
}
