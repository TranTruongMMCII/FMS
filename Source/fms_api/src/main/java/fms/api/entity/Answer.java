package fms.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
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
class AnswerKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "ClassID")
	private Class class_answer;
	
	@ManyToOne
	@JoinColumn(name = "ModuleID")
	private Module module_answer;
	
	@ManyToOne
	@JoinColumn(name = "UserName")
	private Trainee trainee_answer;
	
	@ManyToOne
	@JoinColumn(name = "QuestionID")
	private Question question_answer;
}

@Entity
@Table(name = "Answer")
@EntityListeners(AuditingEntityListener.class)
@IdClass(AnswerKey.class)

public class Answer extends Auditable<String>{
	@Id
	private Class class_answer;
	@Id
	private Module module_answer;
	@Id
	private Trainee trainee_answer;
	@Id
	private Question question_answer;

	@Column(name = "Value")
	private int value;

	public Class getClass_answer() {
		return class_answer;
	}

	public void setClass_answer(Class class_id) {
		this.class_answer = class_id;
	}

	public Module getModule_answer() {
		return module_answer;
	}

	public void setModule_answer(Module module) {
		this.module_answer = module;
	}

	public Trainee getTrainee_answer() {
		return trainee_answer;
	}

	public void setTrainee_answer(Trainee traineeID) {
		this.trainee_answer = traineeID;
	}

	public Question getQuestion_answer() {
		return question_answer;
	}

	public void setQuestion_answer(Question question) {
		this.question_answer = question;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	
	
}
