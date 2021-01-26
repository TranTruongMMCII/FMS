package fms.api.entity;

import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import fms.api.audit.Auditable;

@Entity
@Table(name="Module")
@EntityListeners(AuditingEntityListener.class)
public class Module extends Auditable<String>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ModuleID", nullable = false)
	private long ModuleID;
	
	@ManyToOne
	@JoinColumn(name = "UserName")
	private Admin admin_module;
	
	@Column(name = "ModuleName", nullable = false)
	private String ModuleName;
	
	@Column(name = "StartTime", nullable = false)
	private Date StartTime;

	@Column(name = "EndTime", nullable = false)
	private Date EndTime;
	
	@Column(name = "IsDeleted", nullable = false)
	private boolean IsDeleted;
	
	@Column(name = "FeedbackStartTime", nullable = false)
	private Date FeedbackStartTime;
	
	@Column(name = "FeedbackEndTime", nullable = false)
	private Date FeedbackEndTime;
	
	@ManyToOne
    @JsonIgnore
	@JoinColumn(name = "FeedbackID")
	private Feedback feedback_module;
	
	@OneToMany(mappedBy = "answerKey.moduleID", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Answer> answers;
	
	@JsonIgnore
	@OneToMany(mappedBy = "assignmentKey.moduleId", cascade =  CascadeType.ALL, orphanRemoval = true)
	private List<Assignment> assignments;
	
	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public long getModuleID() {
		return ModuleID;
	}

	public void setModuleID(long moduleID) {
		ModuleID = moduleID;
	}

	

	public Admin getAdmin_module() {
		return admin_module;
	}

	public void setAdmin_module(Admin admin_module) {
		this.admin_module = admin_module;
	}

	public String getModuleName() {
		return ModuleName;
	}

	public void setModuleName(String moduleName) {
		ModuleName = moduleName;
	}

	public Date getStartTime() {
		return StartTime;
	}

	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}

	public Date getEndTime() {
		return EndTime;
	}

	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}

	public boolean isIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		IsDeleted = isDeleted;
	}

	public Date getFeedbackStartTime() {
		return FeedbackStartTime;
	}

	public void setFeedbackStartTime(Date feedbackStartTime) {
		FeedbackStartTime = feedbackStartTime;
	}

	public Date getFeedbackEndTime() {
		return FeedbackEndTime;
	}

	public void setFeedbackEndTime(Date feedbackEndTime) {
		FeedbackEndTime = feedbackEndTime;
	}

	public Feedback getFeedback_module() {
		return feedback_module;
	}

	public void setFeedback_module(Feedback feedback_module) {
		this.feedback_module = feedback_module;
	}

	

}