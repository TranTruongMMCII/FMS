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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import fms.api.audit.Auditable;

@Entity
@Table(name = "Class")
@EntityListeners(AuditingEntityListener.class)
public class Class extends Auditable<String>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ClassID")
	private int classID;
	
	@Column(name = "ClassName")
	private String className;
	
	@Column(name = "Capacity")
	private int capacity;
	
	@Column(name = "StartTime")
	private Date startTime;
	
	@Column(name = "EndTime")
	private Date endTime;
	
	@Column(name = "IsDeleted")
	private Boolean isDeleted;
	
	@OneToMany(mappedBy = "class_answer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Answer> answers;
	
	@OneToMany(mappedBy = "class_enroll", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Enrollment> enrollments;
	
	@OneToMany(mappedBy = "class_assignment", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Assignment> assignments;
	
	
	
	


	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
