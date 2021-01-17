package fms.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import fms.api.audit.Auditable;


@Entity
@Table(name = "Assignment")
@EntityListeners(AuditingEntityListener.class)

public class Assignment extends Auditable<String>{
	@EmbeddedId
	private AssignmentKey assignmentKey;

	@Column(name = "RegistrationCode")
	private String registrationCode;
	
	public String getRegistrationCode() {
		return registrationCode;
	}

	public AssignmentKey getAssignmentKey() {
		return assignmentKey;
	}

	public void setAssignmentKey(AssignmentKey assignmentKey) {
		this.assignmentKey = assignmentKey;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}	
	
}
