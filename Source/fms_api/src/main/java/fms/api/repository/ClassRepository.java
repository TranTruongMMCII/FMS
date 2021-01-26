package fms.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fms.api.entity.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

	@Query(value = "SELECT * FROM class p WHERE isDeleted = 0", nativeQuery = true)
	public List<Class> getAllClass();
	
	Optional<Class> findByClassName(String className);
}
