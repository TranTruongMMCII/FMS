package fms.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fms.api.entity.Assignment;
import fms.api.entity.AssignmentKey;
import fms.api.entity.Class;
import fms.api.entity.Module;
import fms.api.entity.Trainer;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, AssignmentKey>{
	Optional<Assignment> findById(AssignmentKey id);
	
	List<Assignment> findByAssignmentKeyTrainerId(Trainer trainerId);

	List<Assignment> findByAssignmentKeyClassId(Class classId);

	List<Assignment> findByAssignmentKeyModuleId(Module moduleId);
	
	List<Assignment> findByRegistrationCode(String registrationCode);
}
