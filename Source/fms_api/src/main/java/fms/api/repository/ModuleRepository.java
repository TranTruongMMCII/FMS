package fms.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import fms.api.entity.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long>{
	
	@Query(value = "SELECT * FROM module p WHERE isDeleted = 0", nativeQuery = true)
	public List<Module> getAllModule();
	
	Module findModuleByModuleName(String moduleName);



}
