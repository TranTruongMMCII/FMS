package fms.api.controller;

import java.util.ArrayList;
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

import fms.api.dto.AssignmentDto;
import fms.api.entity.AssignmentKey;
import fms.api.entity.Class;
import fms.api.entity.Module;
import fms.api.entity.Trainer;

////import fms.api.repository.ClassRepository;
//import fms.api.repository.ModuleRepository;
//import fms.api.repository.TrainerRepository;

import fms.api.entity.Assignment;

import fms.api.exception.ResourceNotFoundException;
import fms.api.repository.AssignmentRepository;
import fms.api.repository.ClassRepository;
import fms.api.repository.ModuleRepository;
import fms.api.repository.TrainerRepository;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
	

	@Autowired
	AssignmentRepository assignmentRepository;

	@Autowired
	ClassRepository classRepository;

	@Autowired
	ModuleRepository moduleRepository;

	@Autowired
	TrainerRepository trainerRepository;
	
	
	@GetMapping("/getAll")
	public List<AssignmentDto> getAllAssigments(){
		List<Assignment> assignments = assignmentRepository.findAll();
		AssignmentKey aKey = new AssignmentKey();
		List<AssignmentDto> responseAsmDto  = new ArrayList<AssignmentDto>();
		
		for (Assignment obj : assignments) {
			aKey = obj.getAssignmentKey();
			AssignmentDto asmDto = new AssignmentDto();
			
			Class reClass = aKey.getClassId_assign();
			asmDto.setClassID(reClass.getClassID());
			
			Module reModule = aKey.getModuleId_assign();
			asmDto.setModuleID(reModule.getModuleID());
			
			Trainer reTrainer = aKey.getTrainer_assign();
			asmDto.setTrainerID(reTrainer.getUserName());
			
			responseAsmDto.add(asmDto);
		}
		return responseAsmDto;
	}
	
	@GetMapping("/getAssignmentById/{classId}/{moduleId}/{trainerId}")
	public ResponseEntity<AssignmentDto> getAssignmentById(
			@PathVariable(value = "classId") Class classId, @PathVariable(value = "moduleId") Module moduleId, @PathVariable(value = "trainerId") Trainer trainerId) throws ResourceNotFoundException{
		AssignmentKey asmKey = new AssignmentKey(classId, moduleId, trainerId);
		Assignment asm = assignmentRepository.findAssignmentById(asmKey)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment Not Found"));
		AssignmentDto asmDto = new AssignmentDto();

		Class responseClass = asmKey.getClassId_assign();
		asmDto.setClassID(responseClass.getClassID());

		Module responseModule = asmKey.getModuleId_assign();
		asmDto.setModuleID(responseModule.getModuleID());

		Trainer responseTrainer = asmKey.getTrainer_assign();
		asmDto.setTrainerID(responseTrainer.getUserName());

		asmDto.setRegistrationCode(asm.getRegistrationCode());
		return ResponseEntity.ok().body(asmDto);
	}
	
	@GetMapping("/getAssignmentByTrainer/{trainerId}")
	public List<AssignmentDto> getAssignmentByTrainer(@PathVariable(value = "trainerId") Trainer trainerId){
		List<Assignment> asm = assignmentRepository.findByAssignmentKeyTrainerId(trainerId);
		AssignmentKey asmKey = new AssignmentKey();
		List<AssignmentDto> responseAsmDto = new ArrayList<AssignmentDto>();

		for (Assignment obj : asm) {
			asmKey = obj.getAssignmentKey();
			AssignmentDto asmDto = new AssignmentDto();

			Class responseClass = asmKey.getClassId_assign();
			asmDto.setClassID(responseClass.getClassID());

			Module responseModule = asmKey.getModuleId_assign();
			asmDto.setModuleID(responseModule.getModuleID());

			Trainer responseTrainer = asmKey.getTrainer_assign();
			asmDto.setTrainerID(responseTrainer.getUserName());

			asmDto.setRegistrationCode(obj.getRegistrationCode());

			responseAsmDto.add(asmDto);
		}

		return responseAsmDto;
	}
	
	@GetMapping("/getAssignmentByClassId/{classId}")
	public List<AssignmentDto> getAssignmentByTrainerId(@PathVariable(value = "classId") Class classId) {

		List<Assignment> asm = assignmentRepository.findByAssignmentKeyClassId(classId);
		AssignmentKey asmKey = new AssignmentKey();
		List<AssignmentDto> responseAsmDto = new ArrayList<AssignmentDto>();

		for (Assignment obj : asm) {
			asmKey = obj.getAssignmentKey();
			AssignmentDto asmDto = new AssignmentDto();

			Class responseClass = asmKey.getClassId_assign();
			asmDto.setClassID(responseClass.getClassID());

			Module responseModule = asmKey.getModuleId_assign();
			asmDto.setModuleID(responseModule.getModuleID());

			Trainer responseTrainer = asmKey.getTrainer_assign();
			asmDto.setTrainerID(responseTrainer.getUserName());

			asmDto.setRegistrationCode(obj.getRegistrationCode());

			responseAsmDto.add(asmDto);
		}

		return responseAsmDto;
	}

	@GetMapping("/getAssignmentByModuleId/{moduleId}")
	public List<AssignmentDto> getAssignmentByTrainerId(@PathVariable(value = "moduleId") Module moduleId) {

		List<Assignment> asm = assignmentRepository.findByAssignmentKeyModuleId(moduleId);
		AssignmentKey asmKey = new AssignmentKey();
		List<AssignmentDto> responseAsmDto = new ArrayList<AssignmentDto>();

		for (Assignment obj : asm) {
			asmKey = obj.getAssignmentKey();
			AssignmentDto asmDto = new AssignmentDto();

			Class responseClass = asmKey.getClassId_assign();
			asmDto.setClassID(responseClass.getClassID());

			Module responseModule = asmKey.getModuleId_assign();
			asmDto.setModuleID(responseModule.getModuleID());

			Trainer responseTrainer = asmKey.getTrainer_assign();
			asmDto.setTrainerID(responseTrainer.getUserName());

			asmDto.setRegistrationCode(obj.getRegistrationCode());

			responseAsmDto.add(asmDto);
		}

		return responseAsmDto;
	}
	
	@GetMapping("/getAssignmentByRegistrationCode/{registrationCode}")
	public List<AssignmentDto> getAssignmentByRegistrationCode(@PathVariable(value = "registrationCode") String registrationCode) {

		List<Assignment> asm = assignmentRepository.findByRegistrationCode(registrationCode);
		AssignmentKey asmKey = new AssignmentKey();
		List<AssignmentDto> responseAsmDto = new ArrayList<AssignmentDto>();

		for (Assignment obj : asm) {
			asmKey = obj.getAssignmentKey();
			AssignmentDto asmDto = new AssignmentDto();

			Class responseClass = asmKey.getClassId_assign();
			asmDto.setClassID(responseClass.getClassID());

			Module responseModule = asmKey.getModuleId_assign();
			asmDto.setModuleID(responseModule.getModuleID());

			Trainer responseTrainer = asmKey.getTrainer_assign();
			asmDto.setTrainerID(responseTrainer.getUserName());

			asmDto.setRegistrationCode(obj.getRegistrationCode());

			responseAsmDto.add(asmDto);
		}

		return responseAsmDto;
	}

	@PutMapping("/updateAssignment/{classId}/{moduleId}/{trainerId}")
	public ResponseEntity<Assignment> updateAssignment (@PathVariable(value = "classId") Class classId,
			@PathVariable(value = "moduleId") Module moduleId, @PathVariable(value = "trainerId") Trainer trainerId,
			@Validated @RequestBody AssignmentDto asmDto) throws ResourceNotFoundException {
		
		AssignmentKey asmKey = new AssignmentKey(classId, moduleId, trainerId);

		Assignment asm = assignmentRepository.findAssignmentById(asmKey).orElseThrow(() -> new ResourceNotFoundException("Assignment Not Found"));

		Trainer trainer = new Trainer();
		trainer = trainerRepository.findById(asmDto.trainerID).orElseThrow(() -> new ResourceNotFoundException("Trainer Not Found " + asmDto.getTrainerID()));
		
		Assignment addAsm = new Assignment();
		addAsm.setAssignmentKey(new AssignmentKey(classId, moduleId, trainer));
		
		addAsm.setRegistrationCode(asm.getRegistrationCode());

		Assignment updateAsm = assignmentRepository.save(addAsm);
		
		assignmentRepository.delete(asm);
		
		return ResponseEntity.ok().body(updateAsm);
		
	}

	@PostMapping("/addAssignment")
	public Assignment addAssignment(@RequestBody AssignmentDto asmDto) throws ResourceNotFoundException {

		Assignment asm = new Assignment();

		Class cls = classRepository.findById(asmDto.getClassID()).orElseThrow(() -> new ResourceNotFoundException("Class not found"));

		Module module = moduleRepository.findById(asmDto.getModuleID()).orElseThrow(() -> new ResourceNotFoundException("Module not found"));

		Trainer trainer = new Trainer();
		trainer.setUserName(asmDto.getTrainerID());

		asm.setAssignmentKey(new AssignmentKey(cls, module, trainer));
		
		asm.setRegistrationCode(asmDto.getRegistrationCode());
		return assignmentRepository.save(asm);
		
	}

	@DeleteMapping("/deleteAssignment/{classId}/{moduleId}/{trainerId}")
	public Map<String, Boolean> deleteAssignment(
			@PathVariable(value = "classId") Class classId,
			@PathVariable(value = "moduleId") Module moduleId, @PathVariable(value = "trainerId") Trainer trainerId)
			throws ResourceNotFoundException {
		
		AssignmentKey asmKey = new AssignmentKey(classId, moduleId, trainerId);
		
		Assignment asm = assignmentRepository.findById(asmKey)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment Not Found"));
		
		assignmentRepository.delete(asm);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	
	
}
