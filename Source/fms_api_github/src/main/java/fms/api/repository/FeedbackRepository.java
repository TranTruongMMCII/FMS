package fms.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fms.api.entity.Feedback;
//import fms.api.entity.FeedbackGetResult;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	
	@Query("SELECT a FROM Feedback a") 
	List<Feedback> getFeedbackList();
	
	@Query(value = "SELECT * FROM Feedback a ORDER BY a.feedbackID DESC LIMIT 0,5", nativeQuery = true) 
	List<Feedback> getLastFeedback();
	
//	@Query("SELECT f.FeedbackID as FeedbackID, f.Title, admin_feedback.UserName FROM Feedback f")
//	List<Object> getFeedbackList();
	
	  @Modifying
	  @Query(value = "UPDATE feedback f SET f.typeID = :typeid, f.title = :title WHERE f.feedbackID = :id", nativeQuery = true)
	  public void updateFeedback(@Param("id") Long id, @Param("typeid") Long typeId, @Param("title") String title);

	  @Modifying
	  @Query(value = "INSERT INTO feedback (title, userName, typeID, IsDeleted) VALUES (:title, :userName, :typeID, false)", nativeQuery = true)
	  public void insertFeedback(@Param("title") String title, @Param("userName") String userName, @Param("typeID") Long typeId);
	  
}

