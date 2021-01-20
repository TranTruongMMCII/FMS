package fms.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fms.api.entity.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long>{
	@Query(nativeQuery = true, value="SELECT * FROM Module m WHERE m.ModuleName =: moduleName")
	
	Module findModuleByModuleName(@Param ("ModuleName")String moduleName);
	
}