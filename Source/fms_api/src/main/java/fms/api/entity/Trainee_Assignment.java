package fms.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import fms.api.audit.Auditable;

@Entity
@Table(name="Trainee_Assignment")
@EntityListeners(AuditingEntityListener.class)
public class Trainee_Assignment extends Auditable<String>{
	@Id
	@Column(name = "RegistrationCode", nullable = false)
	private String RegistrationCode;
	
	@Column(name = "TraineeID", nullable = false)
	private String TraineeID;

	public String getRegistrationCode() {
		return RegistrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		RegistrationCode = registrationCode;
	}

	public String getTraineeID() {
		return TraineeID;
	}

	public void setTraineeID(String traineeID) {
		TraineeID = traineeID;
	}

	
}
