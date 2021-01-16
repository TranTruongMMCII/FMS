package fms.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	
	@Column(name = "registrationCode")
	private String registrationCode;

	public AssignmentKey getAssignmentKey() {
		return assignmentKey;
	}

	public void setAssignmentKey(AssignmentKey assignmentKey) {
		this.assignmentKey = assignmentKey;
	}


	public String getRegistrationCode() {
		return registrationCode;
	}


	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}


	public Assignment() {
		super();
	}
	
}
