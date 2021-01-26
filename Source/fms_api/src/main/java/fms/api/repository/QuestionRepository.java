package fms.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fms.api.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	
}
