package fms.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fms.api.entity.*;
import fms.api.entity.Class;
import fms.api.entity.Module;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, AnswerKey>{
//	Answer findAnswerById(AnswerKey answerKey);
//	List<Answer> findAnswerByClassId(Class classID);
	@Query(nativeQuery = true, value = "select * from Answer a where a.ClassID =: classID")
	List<Answer> findAnswerByClassId(@Param ("ClassID") int classID);
//	List<Answer> findAnswerByModuleId(Module moduleID);
	@Query(nativeQuery = true, value = "select * from Answer a where a.ModuleID =: moduleID")
	List<Answer> findAnswerByModuleId(@Param ("ModuleID") int moduleID);
//	List<Answer> findAnswerByTraineeId(Trainee traineeID);
	@Query(nativeQuery = true, value = "select * from Answer a where a.TraineeID =: traineeID")
	List<Answer> findAnswerByTraineeId(@Param ("TraineeID") String traineeID);
//	List<Answer> findAnswerByQuestionId(Question questionID);
	@Query(nativeQuery = true, value = "select * from Answer a where a.QuestionID =: questionID")
	List<Answer> findAnswerByQuestionId(@Param ("QuestionID") int questionID);
	List<Answer> findAnswerByValue(Integer integer);
}