package fms.api.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import fms.api.audit.Auditable;

@Entity
@Table(name="Topic")
@EntityListeners(AuditingEntityListener.class)
public class Topic extends Auditable<String>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TopicID", nullable = false)
	private int TopicID;
	
	@Column(name = "TopicName", nullable = false)
	private String TopicName;
	
	@OneToMany(mappedBy = "topic_question", cascade =  CascadeType.ALL,  orphanRemoval = true)
	private List<Question> questions;

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public int getTopicID() {
		return TopicID;
	}

	public void setTopicID(int topicID) {
		TopicID = topicID;
	}

	public String getTopicName() {
		return TopicName;
	}

	public void setTopicName(String topicName) {
		TopicName = topicName;
	}

	
}
