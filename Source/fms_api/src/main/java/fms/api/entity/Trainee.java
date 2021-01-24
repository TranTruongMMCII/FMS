package fms.api.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import fms.api.audit.Auditable;

@Entity
@Table(name = "Trainee")
@EntityListeners(AuditingEntityListener.class)
public class Trainee extends Auditable<String>{

	@Id
	@Column(name = "UserName")
	private String userName;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Phone")
	private String phone;
	
	@Column(name = "Address")
	private String address;
	
	@Column(name = "IsActive")
	private Boolean isActive;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "ActivationCode")
	private String activationCode;
	
	@Column(name = "ResetPasswordCode")
	private String resetPasswordCode;
	
	@OneToMany(mappedBy = "answerKey.traineeID", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Answer> answer;
	
	@OneToMany(mappedBy = "trainee_enroll", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Enrollment> enrollments;
	
	
	

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public List<Answer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public String getResetPasswordCode() {
		return resetPasswordCode;
	}

	public void setResetPasswordCode(String resetPasswordCode) {
		this.resetPasswordCode = resetPasswordCode;
	}

	
}
