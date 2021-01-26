package fms.api.dto;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerDTO {

	@NonNull
	private int classID;
	
	@NonNull
	private long moduleID;
	
	@NonNull
	private String traineeID;
	
	@NonNull
	private int questionID;
	
	@NonNull
	private int value;
	
	private String comment;

	public int getClassID() {
		return classID;
	}

	public void setClassID(long l) {
		this.classID = (int) l;
	}

	public long getModuleID() {
		return moduleID;
	}

	public void setModuleID(long moduleID) {
		this.moduleID = moduleID;
	}

	public String getTraineeID() {
		return traineeID;
	}

	public void setTraineeID(String traineeID) {
		this.traineeID = traineeID;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}
	
	
}
