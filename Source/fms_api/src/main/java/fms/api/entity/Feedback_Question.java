package fms.api.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fms.api.audit.Auditable;



@Entity
@Table(name="Feedback_Question")
@EntityListeners(AuditingEntityListener.class)
//@IdClass(Feedback_QuestionKey.class)
public class Feedback_Question extends Auditable<String>{
	@Id
	private Feedback_QuestionKey feedback_QuestionKey;

	public Feedback_QuestionKey getFeedback_QuestionKey() {
		return feedback_QuestionKey;
	}

	public void setFeedback_QuestionKey(Feedback_QuestionKey feedback_QuestionKey) {
		this.feedback_QuestionKey = feedback_QuestionKey;
	}

	public Feedback_Question(Feedback_QuestionKey feedback_QuestionKey) {
		super();
		this.feedback_QuestionKey = feedback_QuestionKey;
	}

	public Feedback_Question() {
		super();
	}
	
	
}


























