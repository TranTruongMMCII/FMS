package fms.api.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import fms.api.audit.Auditable;


@Embeddable
class EnrollKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "ClassID")
	private Class class_enroll;
	
	@ManyToOne
	@JoinColumn(name = "TraineeID")
	private Trainee trainee_enroll;
}

@Entity
@Table(name = "Enrollment")
@EntityListeners(AuditingEntityListener.class)
@IdClass(EnrollKey.class)
public class Enrollment extends Auditable<String>{

	@Id
	private Class class_enroll;
	@Id
	private Trainee trainee_enroll;
	public Class getClass_enroll() {
		return class_enroll;
	}
	public void setClass_enroll(Class classID) {
		this.class_enroll = classID;
	}
	public Trainee getTrainee_enroll() {
		return trainee_enroll;
	}
	public void setTrainee_enroll(Trainee trainee) {
		this.trainee_enroll = trainee;
	}

	
}
