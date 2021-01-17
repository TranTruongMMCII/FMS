package fms.api.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fms.api.audit.Auditable;

@Entity
@Table(name = "Feedback")
@EntityListeners(AuditingEntityListener.class)

public class Feedback extends Auditable<String>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FeedbackID", nullable = false)
	private long FeedbackID;
	
	@Column(name = "Title", nullable = false)
	private String Title;
	
	@Column(name = "IsDeleted", nullable = false)
	private boolean IsDeleted;
	
	@ManyToOne
	@JoinColumn(name = "UserName")
	
	private Admin AdminID;

	@ManyToOne
	@JoinColumn(name = "TypeID")
	
	private TypeFeedback feedback_typeID;

	@OneToMany(mappedBy = "feedback_feedback_question", cascade = CascadeType.ALL, orphanRemoval =  true)
	private List<Feedback_Question> feedback_Questions;
	
	@OneToMany(mappedBy = "feedback_module", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Module> modules;
	
	@JsonBackReference
	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	
	@JsonBackReference
	public List<Feedback_Question> getFeedback_Questions() {
		return feedback_Questions;
	}

	public void setFeedback_Questions(List<Feedback_Question> feedback_Questions) {
		this.feedback_Questions = feedback_Questions;
	}
	
	@JsonManagedReference
	public TypeFeedback getFeedback_typeID() {
		return feedback_typeID;
	}

	public void setFeedback_typeID(TypeFeedback feedback_typeID) {
		this.feedback_typeID = feedback_typeID;
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
	
	@JsonManagedReference
	public Admin getAdminID() {
		return AdminID;
	}

	public void setAdminID(Admin adminID) {
		AdminID = adminID;
	}

	public boolean isIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		IsDeleted = isDeleted;
	}

}



































