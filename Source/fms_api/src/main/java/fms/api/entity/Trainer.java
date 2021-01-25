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

import com.fasterxml.jackson.annotation.JsonIgnore;

import fms.api.audit.Auditable;

@Entity
@Table(name = "Trainer")
@EntityListeners(AuditingEntityListener.class)
public class Trainer extends Auditable<String>{

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
	
	@Column(name = "IdSkill")
	private int idSkill;
	
	@Column(name = "ActivationCode")
	private String activationCode;
	
	@Column(name = "ResetPasswordCode")
	private String resetPasswordCode;
	
	@Column(name = "IsReceiveNotification")
	private Boolean isReceiveNotification;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "assignmentKey.trainerId", cascade =  CascadeType.ALL, orphanRemoval = true)
	private List<Assignment> assignments;
	
	
	
	
	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
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

	public int getIdSkill() {
		return idSkill;
	}

	public void setIdSkill(int idSkill) {
		this.idSkill = idSkill;
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

	public Boolean getIsReceiveNotification() {
		return isReceiveNotification;
	}

	public void setIsReceiveNotification(Boolean isReceiveNotification) {
		this.isReceiveNotification = isReceiveNotification;
	}
}