package fms.api.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import fms.api.audit.Auditable;

@Entity
@Table(name = "TypeFeedback")
@EntityListeners(AuditingEntityListener.class)
public class TypeFeedback extends Auditable<String>{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TypeID", nullable = false)
	private long TypeID;
	
	@Column(name = "TypeName", nullable = false)
	private String TypeName;
	
	@Column(name = "IsDeleted", nullable = false)
	private boolean IsDeleted;
	
	@OneToMany(mappedBy = "feedback_typeID", cascade =  CascadeType.ALL, orphanRemoval = true)	
	private List<Feedback> feedbacks;
	
	@JsonBackReference
	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public long getTypeID() {
		return TypeID;
	}

	public void setTypeID(long typeID) {
		TypeID = typeID;
	}

	public String getTypeName() {
		return TypeName;
	}

	public void setTypeName(String typeName) {
		TypeName = typeName;
	}

	public boolean isIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		IsDeleted = isDeleted;
	}

}
