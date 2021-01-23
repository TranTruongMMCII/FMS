package fms.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fms.api.entity.Feedback;
import fms.api.entity.Feedback_Question;
import fms.api.entity.Feedback_QuestionKey;

@Repository
public interface FeedbackQuestionRepository extends JpaRepository<Feedback_Question, Feedback_QuestionKey> {

	  @Query(value = "SELECT * FROM feedback_question u WHERE u.feedback_feedback_question = ?1", nativeQuery = true)
	  List<Feedback_Question> findbyFeedbackID(Long feedbackID);
	  
	  @Query(value = "SELECT * FROM Feedback_Question u WHERE u.feedback_feedback_question = ?1 AND u.question_feedback_question = ?2", nativeQuery = true)
	  Feedback_Question findbyQuestionID(Long feedbackID, Long questionID);

	  @Modifying
	  @Query(value = "INSERT INTO Feedback_Question (feedback_feedback_question, question_feedback_question) VALUES (:FeedbackID, :QuestionID)", nativeQuery = true)
	  public void insertFeedbackDetail(@Param("FeedbackID") Long feedbackID, @Param("QuestionID") Long questionID);
	  
}
