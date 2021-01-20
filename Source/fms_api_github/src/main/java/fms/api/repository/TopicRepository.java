package fms.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fms.api.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
	
}
