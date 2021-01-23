package fms.api.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Embeddable
public class Feedback_QuestionKey implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "feedback_feedback_question", referencedColumnName = "FeedbackID", insertable = false)
	private Feedback feedback_feedback_question;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "question_feedback_question", referencedColumnName = "QuestionID", insertable = false)
	private Question question_feedback_question;

	@JsonIgnore
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
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Feedback_QuestionKey(Feedback feedback_feedback_question, Question question_feedback_question) {
		super();
		this.feedback_feedback_question = feedback_feedback_question;
		this.question_feedback_question = question_feedback_question;
	}
	
	public Feedback_QuestionKey() {
		super();
	}
	
}
