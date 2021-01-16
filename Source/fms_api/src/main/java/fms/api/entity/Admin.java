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

import fms.api.audit.Auditable;

@Entity
@Table(name="Admin")
@EntityListeners(AuditingEntityListener.class)
public class Admin extends Auditable<String>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UserName", nullable = false)
	private String UserName;
	
	@Column(name = "Name", nullable = false)
	private String Name;
	
	@Column(name = "Email", nullable = false)
	private String Email;
	
	@Column(name = "Password", nullable = false)
	private boolean Password;
	
	@OneToMany(mappedBy = "admin_module", cascade =  CascadeType.ALL, orphanRemoval = true)
	private List<Module>modules;
	
	@OneToMany(mappedBy = "admin_feedback", cascade =  CascadeType.ALL, orphanRemoval =  true)
	private List<Feedback> feedbacks;
	
	

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public boolean isPassword() {
		return Password;
	}

	public void setPassword(boolean password) {
		Password = password;
	}
}
































