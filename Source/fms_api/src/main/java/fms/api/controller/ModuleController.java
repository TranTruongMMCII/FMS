package fms.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fms.api.repository.ModuleRepository;
import fms.api.entity.Module;
import fms.api.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/module")
public class ModuleController {
	
	@Autowired
	private ModuleRepository moduleRepository;

	@GetMapping("/getAllModule")
	public List<Module> getAllModule(){
	 return moduleRepository.findAll();
	}
	
	@GetMapping("getModuleById/{moduleId}")
	public ResponseEntity<Module> getCourseById(@PathVariable(value = "moduleId") long moduleId) throws ResourceNotFoundException{
		Module course = 
				moduleRepository
				.findById(moduleId).orElseThrow(() -> new ResourceNotFoundException("User not found ::" + moduleId));
		return ResponseEntity.ok().body(course);
	}
	
	@GetMapping("getModuleByName/{moduleName}")
	public ResponseEntity<Module> getModuleByName(@PathVariable(value = "moduleName") String moduleName) throws ResourceNotFoundException {
		Module module = moduleRepository.findModuleByModuleName(moduleName);
		return ResponseEntity.ok().body(module);
	}
	
	@PostMapping("/addModule")
	public Module addCourse(@Validated @RequestBody Module course) {
		return moduleRepository.save(course);
	}
	
	@PutMapping("/updateModule/{moduleId}")
	public ResponseEntity<Module> updateUser(@PathVariable(value = "moduleId") long moduleId, @Validated @RequestBody Module moduleDetails) throws ResourceNotFoundException{
		Module module = moduleRepository
				.findById(moduleId)
				.orElseThrow(() -> new ResourceNotFoundException("Module not found ::" + moduleId));
		module.setAdmin_module(moduleDetails.getAdmin_module());
		module.setModuleName(moduleDetails.getModuleName());
		module.setStartTime(moduleDetails.getStartTime());
		module.setEndTime(moduleDetails.getEndTime());
		module.setIsDeleted(moduleDetails.isIsDeleted());;
		module.setFeedback_module(moduleDetails.getFeedback_module());
		module.setFeedbackStartTime(moduleDetails.getFeedbackStartTime());
		module.setFeedbackEndTime(moduleDetails.getFeedbackEndTime());
		final Module updateModule  = moduleRepository.save(module);
		return ResponseEntity.ok(updateModule);
	}
	
	@DeleteMapping("/deleteModue/{moduleId}")
	public Map<String, Boolean> deleteCourse(@PathVariable(value = "moduleId") long moduleId) throws ResourceNotFoundException{
		Module course = moduleRepository
				.findById(moduleId)
				.orElseThrow(() -> new ResourceNotFoundException("Module not found ::" + moduleId));
		moduleRepository.delete(course);
		Map<String,Boolean> respone = new HashMap<>();
		respone.put("deleted", Boolean.TRUE);
		return respone;
	}


}