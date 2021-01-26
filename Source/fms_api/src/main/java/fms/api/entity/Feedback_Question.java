package fms.api.entity;

import java.io.Serializable;

<<<<<<< HEAD
import javax.persistence.Embeddable;
=======
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
>>>>>>> bbc4404227759715db241878bf63d4aa42df7f40
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
<<<<<<< HEAD
=======
import javax.persistence.MapsId;
>>>>>>> bbc4404227759715db241878bf63d4aa42df7f40
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fms.api.audit.Auditable;

<<<<<<< HEAD
@Embeddable
class Feedback_QuestionKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "FeedbackID")
	private Feedback feedback_feedback_question;
	
	@ManyToOne
	@JoinColumn(name = "QuestionID")
	private Question question_feedback_question;
}

@Entity
@Table(name="Feedback_Question")
@EntityListeners(AuditingEntityListener.class)
@IdClass(Feedback_QuestionKey.class)
public class Feedback_Question extends Auditable<String>{
	@Id
	private Feedback feedback_feedback_question;

	@Id
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
=======
@Entity
@Table(name="Feedback_Question")
@EntityListeners(AuditingEntityListener.class)
public class Feedback_Question extends Auditable<String>{
	@EmbeddedId
	private Feedback_QuestionKey Feedback_QuestionKey;

	public Feedback_QuestionKey getFeedback_QuestionKey() {
		return Feedback_QuestionKey;
	}

	public void setFeedback_QuestionKey(Feedback_QuestionKey feedback_QuestionKey) {
		Feedback_QuestionKey = feedback_QuestionKey;
	}
	
>>>>>>> bbc4404227759715db241878bf63d4aa42df7f40
}


























