package fms.api.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AnswerKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ClassID", referencedColumnName = "ClassID", insertable = false)
	private Class classID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID", insertable = false)
	private Module moduleID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TraineeID", referencedColumnName = "UserName", insertable = false)
	private Trainee traineeID;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "QuestionID", referencedColumnName = "QuestionID", insertable = false)
	private Question questionID;

	public Class getClassID() {
		return classID;
	}

	public void setClassID(Class classID) {
		this.classID = classID;
	}

	public Module getModuleID() {
		return moduleID;
	}

	public void setModuleID(Module moduleID) {
		this.moduleID = moduleID;
	}

	public Trainee getTraineeID() {
		return traineeID;
	}

	public void setTraineeID(Trainee traineeID) {
		this.traineeID = traineeID;
	}

	public Question getQuestionID() {
		return questionID;
	}

	public void setQuestionID(Question questionID) {
		this.questionID = questionID;
	}

	public AnswerKey(Class classID, Module moduleID, Trainee traineeID, Question questionID) {
		super();
		this.classID = classID;
		this.moduleID = moduleID;
		this.traineeID = traineeID;
		this.questionID = questionID;
	}

	public AnswerKey() {
		super();
	}
}
