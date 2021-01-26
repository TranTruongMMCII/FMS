package fms.api.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import fms.api.audit.Auditable;

@Entity
@Table(name = "Answer")
@EntityListeners(AuditingEntityListener.class)
public class Answer extends Auditable<String>{
	@EmbeddedId
	private AnswerKey answerKey;
	
	@Column(name = "Value")
	private int value;
<<<<<<< HEAD
	
	@Column(name = "Comment")
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
=======
>>>>>>> bbc4404227759715db241878bf63d4aa42df7f40

	public AnswerKey getAnswerKey() {
		return answerKey;
	}

	public void setAnswerKey(AnswerKey answerKey) {
		this.answerKey = answerKey;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Answer(AnswerKey answerKey, int value) {
		super();
		this.answerKey = answerKey;
		this.value = value;
	}

	public Answer() {
		super();
	}
}