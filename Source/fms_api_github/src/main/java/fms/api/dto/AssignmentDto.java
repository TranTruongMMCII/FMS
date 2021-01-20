package fms.api.dto;
import org.springframework.lang.NonNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import fms.api.entity.Class;
import fms.api.entity.Module;
import fms.api.entity.Trainer;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssignmentDto {
//	@NonNull
//	public long classId;
//	
//	@NonNull
//	public long moduleId;
//	
//	@NonNull
//	public String trainerId;
//	
//	public String registrationCode;
//
//	public long getClassId() {
//		return classId;
//	}
//
//	public void setClassId(long classId) {
//		this.classId = classId;
//	}
//
//	public long getModuleId() {
//		return moduleId;
//	}
//
//	public void setModuleId(long moduleId) {
//		this.moduleId = moduleId;
//	}
//
//	public String getTrainerId() {
//		return trainerId;
//	}
//
//	public void setTrainerId(String trainerId) {
//		this.trainerId = trainerId;
//	}
//
//	public String getRegistrationCode() {
//		return registrationCode;
//	}
//
//	public void setRegistrationCode(String registrationCode) {
//		this.registrationCode = registrationCode;
//	}
	
	@NonNull
	public Class classId;
	
	@NonNull
	public Module moduleId;
	
	@NonNull
	public Trainer trainerId;
	
	public String registrationCode;

	public Class getClassId() {
		return classId;
	}

	public void setClassId(Class classId) {
		this.classId = classId;
	}

	public Module getModuleId() {
		return moduleId;
	}

	public void setModuleId(Module moduleId) {
		this.moduleId = moduleId;
	}

	public Trainer getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(Trainer trainerId) {
		this.trainerId = trainerId;
	}

	public String getRegistrationCode() {
		return registrationCode;
	}

	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}
	
	
	
	
}