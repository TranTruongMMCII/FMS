package fms.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fms.api.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}
