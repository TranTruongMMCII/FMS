package fms.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fms.api.entity.TypeFeedback;

@Repository
public interface TypeFeedbackRepository extends JpaRepository<TypeFeedback, Long>{

}
