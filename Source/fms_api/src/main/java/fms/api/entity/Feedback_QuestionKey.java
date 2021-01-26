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
	@JoinColumn(name = "FeedbackID", referencedColumnName = "FeedbackID", insertable = false)
	private Feedback feedbackID;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "QuestionID", referencedColumnName = "QuestionID", insertable = false)
	private Question questionID;

	
	
	public Feedback_QuestionKey(Feedback feedbackID, Question questionID) {
		super();
		this.feedbackID = feedbackID;
		this.questionID = questionID;
	}



	public Feedback getFeedbackID() {
		return feedbackID;
	}



	public void setFeedbackID(Feedback feedbackID) {
		this.feedbackID = feedbackID;
	}



	public Question getQuestionID() {
		return questionID;
	}



	public void setQuestionID(Question questionID) {
		this.questionID = questionID;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Feedback_QuestionKey() {
		super();
	}
	
}
