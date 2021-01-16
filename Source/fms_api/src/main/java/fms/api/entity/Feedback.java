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

import fms.api.audit.Auditable;

@Entity
@Table(name = "Feedback")
@EntityListeners(AuditingEntityListener.class)

public class Feedback extends Auditable<String>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FeedbackID", nullable = false)
	private long FeedbackID;
	
	@Column(name = "Title", nullable = false)
	private String Title;
	
	@ManyToOne
	@JoinColumn(name = "UserName")
	private Admin admin_feedback;
	
	
	@Column(name = "IsDeleted", nullable = false)
	private boolean IsDeleted;

	@ManyToOne
	@JoinColumn(name = "TypeID")
	private TypeFeedback typefeedback_feedback;

	@OneToMany(mappedBy = "feedback_feedback_question", cascade = CascadeType.ALL, orphanRemoval =  true)
	private List<Feedback_Question> feedback_Questions;
	
	@OneToMany(mappedBy = "feedback_module", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Module> modules;
	
	
	
	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public TypeFeedback getTypefeedback_feedback() {
		return typefeedback_feedback;
	}

	public void setTypefeedback_feedback(TypeFeedback typefeedback_feedback) {
		this.typefeedback_feedback = typefeedback_feedback;
	}


	public List<Feedback_Question> getFeedback_Questions() {
		return feedback_Questions;
	}

	public void setFeedback_Questions(List<Feedback_Question> feedback_Questions) {
		this.feedback_Questions = feedback_Questions;
	}

	public long getFeedbackID() {
		return FeedbackID;
	}

	public void setFeedbackID(long feedbackID) {
		FeedbackID = feedbackID;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public Admin getAdmin_feedback() {
		return admin_feedback;
	}

	public void setAdmin_feedback(Admin admin_feedback) {
		this.admin_feedback = admin_feedback;
	}

	public boolean isIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		IsDeleted = isDeleted;
	}

}



































