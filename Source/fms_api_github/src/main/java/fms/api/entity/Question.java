package fms.api.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fms.api.audit.Auditable;

@Entity
@Table(name = "Question")
@EntityListeners(AuditingEntityListener.class)
public class Question extends Auditable<String>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QuestionID")
	private int questionID;
	
	@ManyToOne
	@JoinColumn(name = "TopicID")
	private Topic topic_question;
	
	@Column(name = "QuestionContent")
	private String quesionContent;
	
	private Boolean isDeleted;
	
	@OneToMany(mappedBy = "answerKey.questionID", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Answer> answers;
	
	@OneToMany(mappedBy = "question_feedback_question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Feedback_Question> feedback_Questions;

	@JsonIgnore
	public List<Feedback_Question> getFeedback_Questions() {
		return feedback_Questions;
	}

	public void setFeedback_Questions(List<Feedback_Question> feedback_Questions) {
		this.feedback_Questions = feedback_Questions;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	@JsonIgnore
	public Topic getTopic_question() {
		return topic_question;
	}

	public void setTopic_question(Topic topic_question) {
		this.topic_question = topic_question;
	}

	public String getQuesionContent() {
		return quesionContent;
	}

	public void setQuesionContent(String quesionContent) {
		this.quesionContent = quesionContent;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
