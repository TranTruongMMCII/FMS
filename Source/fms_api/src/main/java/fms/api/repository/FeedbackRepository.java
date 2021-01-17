package fms.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fms.api.entity.Feedback;
import fms.api.entity.FeedbackGetResult;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	
	@Query("SELECT a FROM Feedback a") 
	List<Feedback> getFeedbackList();
	
//	@Query("SELECT f.FeedbackID as FeedbackID, f.Title, admin_feedback.UserName FROM Feedback f")
//	List<Object> getFeedbackList();
}
