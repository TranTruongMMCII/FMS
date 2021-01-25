package fms.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import fms.api.audit.Auditable;

@Entity
@Table(name="Trainee_Comment")
@EntityListeners(AuditingEntityListener.class)
public class Trainee_Comment extends Auditable<String>{
	@Id
	@Column(name = "ClassID", nullable = false)
	private long ClassID;
	
	
	@Column(name = "ModuleID", nullable = false)
	private long ModuleID;
	
	@Column(name = "TraineeID", nullable = false)
	private String TraineeID;
	
	@Column(name = "Comment", nullable = false)
	private String Comment;

	public long getClassID() {
		return ClassID;
	}

	public void setClassID(long classID) {
		ClassID = classID;
	}

	public long getModuleID() {
		return ModuleID;
	}

	public void setModuleID(long moduleID) {
		ModuleID = moduleID;
	}

	public String getTraineeID() {
		return TraineeID;
	}

	public void setTraineeID(String traineeID) {
		TraineeID = traineeID;
	}

	public String getComment() {
		return Comment;
	}

	public void setComment(String comment) {
		Comment = comment;
	}

	
}





















