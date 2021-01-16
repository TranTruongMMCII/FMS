package fms.api.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AssignmentKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ModuleID", referencedColumnName = "ModuleID", insertable = false)
	private Module moduleId_assign;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ClassID" , referencedColumnName = "ClassID", insertable = false)
	private Class classId_assign;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserName", referencedColumnName = "UserName", insertable = false)
	private Trainer trainer_assign;

	public Module getModuleId_assign() {
		return moduleId_assign;
	}

	public void setModuleId_assign(Module moduleId_assign) {
		this.moduleId_assign = moduleId_assign;
	}

	public Class getClassId_assign() {
		return classId_assign;
	}

	public void setClassId_assign(Class classId_assign) {
		this.classId_assign = classId_assign;
	}

	public Trainer getTrainer_assign() {
		return trainer_assign;
	}

	public void setTrainer_assign(Trainer trainer_assign) {
		this.trainer_assign = trainer_assign;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classId_assign == null) ? 0 : classId_assign.hashCode());
		result = prime * result + ((moduleId_assign == null) ? 0 : moduleId_assign.hashCode());
		result = prime * result + ((trainer_assign == null) ? 0 : trainer_assign.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssignmentKey other = (AssignmentKey) obj;
		if (classId_assign == null) {
			if (other.classId_assign != null)
				return false;
		} else if (!classId_assign.equals(other.classId_assign))
			return false;
		if (moduleId_assign == null) {
			if (other.moduleId_assign != null)
				return false;
		} else if (!moduleId_assign.equals(other.moduleId_assign))
			return false;
		if (trainer_assign == null) {
			if (other.trainer_assign != null)
				return false;
		} else if (!trainer_assign.equals(other.trainer_assign))
			return false;
		return true;
	}
	
	public AssignmentKey(){
		super();
	}


	public AssignmentKey(Class classId_assign, Module moduleId_assign, Trainer trainer_assign) {
		super();
		this.moduleId_assign = moduleId_assign;
		this.classId_assign = classId_assign;
		this.trainer_assign = trainer_assign;
	}
	
	public AssignmentKey(Trainer trainerId2) {
		// TODO Auto-generated constructor stub
	}
	
}