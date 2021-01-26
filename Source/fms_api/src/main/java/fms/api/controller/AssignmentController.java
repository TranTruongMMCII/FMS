package fms.api.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.fasterxml.jackson.databind.ser.std.ClassSerializer;

import fms.api.dto.AssignmentDto;
import fms.api.entity.Assignment;
import fms.api.entity.AssignmentKey;
import fms.api.entity.Class;
import fms.api.entity.Module;
import fms.api.entity.Trainer;
import fms.api.exception.ResourceNotFoundException;
import fms.api.repository.AssignmentRepository;
import fms.api.repository.ClassRepository;
import fms.api.repository.ModuleRepository;
import fms.api.repository.TrainerRepository;

@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {

	@Autowired
	AssignmentRepository asmRepository;

	@Autowired
	ClassRepository classRepository;

	@Autowired
	ModuleRepository moduleRepository;

	@Autowired
	TrainerRepository trainerRepository;

	@GetMapping("/getAll")

	public List<AssignmentDto> getAllAssignment() {
		List<Assignment> asm = asmRepository.findAll();
		AssignmentKey asmKey = new AssignmentKey();
		List<AssignmentDto> responseAsmDto = new ArrayList<AssignmentDto>();

		for (Assignment obj : asm) {
			asmKey = obj.getAssignmentKey();
			AssignmentDto asmDto = new AssignmentDto();

			Class responseClass = asmKey.getClassId();
			asmDto.setClassId(responseClass.getClassID());

			Module responseModule = asmKey.getModuleId();
			asmDto.setModuleId(responseModule.getModuleID());

			Trainer responseTrainer = asmKey.getTrainerId();
			asmDto.setTrainerId(responseTrainer.getUserName());
			
			

			asmDto.setRegistrationCode(obj.getRegistrationCode());

			responseAsmDto.add(asmDto);
		}

		return responseAsmDto;
	}



	@GetMapping("/getAssignmentById/{classId}/{moduleId}/{trainerId}")
	public ResponseEntity<Assignment> getAssignmentById(
			@PathVariable(value = "classId") Class classId,
			@PathVariable(value = "moduleId") Module moduleId, @PathVariable(value = "trainerId") Trainer trainerId)
			throws ResourceNotFoundException {

		AssignmentKey asmKey = new AssignmentKey(classId, moduleId, trainerId);
		Assignment asmDto = asmRepository.findById(asmKey)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment Not Found"));
//		AssignmentDto asmDto = new AssignmentDto();
//
//		Class responseClass = asmKey.getClassId();
//		asmDto.setClassId(responseClass);
//
//		Module responseModule = asmKey.getModuleId();
//		asmDto.setModuleId(responseModule);
//
//		Trainer responseTrainer = asmKey.getTrainerId();
//		asmDto.setTrainerId(responseTrainer);
//
//		asmDto.setRegistrationCode(asm.getRegistrationCode());
		return ResponseEntity.ok().body(asmDto);
	}
//
	@GetMapping("/getAssignmentByTrainerId/{trainerId}")
	public List<Assignment> getAssignmentByTrainerId(@PathVariable(value = "trainerId") Trainer trainerId) {

		List<Assignment> asm = asmRepository.findByAssignmentKeyTrainerId(trainerId);
		AssignmentKey asmKey = new AssignmentKey();
		List<AssignmentDto> responseAsmDto = new ArrayList<AssignmentDto>();

		for (Assignment obj : asm) {
			asmKey = obj.getAssignmentKey();
			AssignmentDto asmDto = new AssignmentDto();

			Class responseClass = asmKey.getClassId();
			asmDto.setClassId(responseClass.getClassID());

			Module responseModule = asmKey.getModuleId();
			asmDto.setModuleId(responseModule.getModuleID());

			Trainer responseTrainer = asmKey.getTrainerId();
			asmDto.setTrainerId(responseTrainer.getUserName());

			asmDto.setRegistrationCode(obj.getRegistrationCode());

			responseAsmDto.add(asmDto);
		}

		return asm;
	}
	
	public String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        return sdf.format(new Date());
    }
	

	@GetMapping("/getAssignmentByClassId/{classId}")
	public List<Assignment> getAssignmentByTrainerId(@PathVariable(value = "classId") Class classId) {

		List<Assignment> asm = asmRepository.findByAssignmentKeyClassId(classId);
		AssignmentKey asmKey = new AssignmentKey();
		List<AssignmentDto> responseAsmDto = new ArrayList<AssignmentDto>();

		for (Assignment obj : asm) {
			asmKey = obj.getAssignmentKey();
			AssignmentDto asmDto = new AssignmentDto();

			Class responseClass = asmKey.getClassId();
			asmDto.setClassId(responseClass.getClassID());

			Module responseModule = asmKey.getModuleId();
			asmDto.setModuleId(responseModule.getModuleID());

			Trainer responseTrainer = asmKey.getTrainerId();
			asmDto.setTrainerId(responseTrainer.getUserName());

			asmDto.setRegistrationCode(obj.getRegistrationCode());

			responseAsmDto.add(asmDto);
		}

		return asm;
	}

	@GetMapping("/getAssignmentByModuleId/{moduleId}")
	public List<AssignmentDto> getAssignmentByTrainerId(@PathVariable(value = "moduleId") Module moduleId) {

		List<Assignment> asm = asmRepository.findByAssignmentKeyModuleId(moduleId);
		AssignmentKey asmKey = new AssignmentKey();
		List<AssignmentDto> responseAsmDto = new ArrayList<AssignmentDto>();

		for (Assignment obj : asm) {
			asmKey = obj.getAssignmentKey();
			AssignmentDto asmDto = new AssignmentDto();

			Class responseClass = asmKey.getClassId();
			asmDto.setClassId(responseClass.getClassID());

			Module responseModule = asmKey.getModuleId();
			asmDto.setModuleId(responseModule.getModuleID());

			Trainer responseTrainer = asmKey.getTrainerId();
			asmDto.setTrainerId(responseTrainer.getUserName());

			asmDto.setRegistrationCode(obj.getRegistrationCode());

			responseAsmDto.add(asmDto);
		}

		return responseAsmDto;
	}
	
	@GetMapping("/getAssignmentByRegistrationCode/{registrationCode}")
	public List<Assignment> getAssignmentByRegistrationCode(@PathVariable(value = "registrationCode") String registrationCode) {

		List<Assignment> asm = asmRepository.findByRegistrationCode(registrationCode);
		AssignmentKey asmKey = new AssignmentKey();
		List<AssignmentDto> responseAsmDto = new ArrayList<AssignmentDto>();

		for (Assignment obj : asm) {
			asmKey = obj.getAssignmentKey();
			AssignmentDto asmDto = new AssignmentDto();

			Class responseClass = asmKey.getClassId();
			asmDto.setClassId(responseClass.getClassID());

			Module responseModule = asmKey.getModuleId();
			asmDto.setModuleId(responseModule.getModuleID());

			Trainer responseTrainer = asmKey.getTrainerId();
			asmDto.setTrainerId(responseTrainer.getUserName());

			asmDto.setRegistrationCode(obj.getRegistrationCode());

			responseAsmDto.add(asmDto);
		}

		return asm;
	}

	@PutMapping("/updateAssignment/{classId}/{moduleId}/{trainerId}")
	public ResponseEntity<Assignment> updateAssignment (@PathVariable(value = "classId") Class classId,
			@PathVariable(value = "moduleId") Module moduleId, @PathVariable(value = "trainerId") Trainer trainerId,
			@Validated @RequestBody AssignmentDto asmDto) throws ResourceNotFoundException {
		
		AssignmentKey asmKey = new AssignmentKey(classId, moduleId, trainerId);

		Assignment asm = asmRepository.findById(asmKey).orElseThrow(() -> new ResourceNotFoundException("Assignment Not Found"));

		Trainer trainer = new Trainer();
		trainer = trainerRepository.findById(asmDto.getTrainerId()).orElseThrow(() -> new ResourceNotFoundException("Trainer Not Found " + asmDto.getTrainerId()));
		
		Assignment addAsm = new Assignment();
		addAsm.setAssignmentKey(new AssignmentKey(classId, moduleId, trainer));
		
		addAsm.setRegistrationCode(asm.getRegistrationCode());

		Assignment updateAsm = asmRepository.save(addAsm);
		
		asmRepository.delete(asm);
		
		return ResponseEntity.ok().body(updateAsm);
		
	}

	@PostMapping("/addAssignment")
	public Assignment addAssignment(@RequestBody AssignmentDto asmDto) throws ResourceNotFoundException {

		Assignment asm = new Assignment();

		Class cls = classRepository.findById((long) asmDto.getClassId()).orElseThrow(() -> new ResourceNotFoundException("Class not found"));

		Module module = moduleRepository.findById(asmDto.getModuleId()).orElseThrow(() -> new ResourceNotFoundException("Module not found"));

		Trainer trainer = new Trainer();
		trainer.setUserName(asmDto.getTrainerId());

		asm.setAssignmentKey(new AssignmentKey(cls, module, trainer));
		
		asm.setRegistrationCode("CL" + Long.toString(asm.getAssignmentKey().getClassId().getClassID()) + "M"
              + Long.toString(asm.getAssignmentKey().getModuleId().getModuleID()) + "T"
              + getTimestamp());
		return asmRepository.save(asm);
		
	}
	
	@DeleteMapping("/deleteAssignment/{classId}/{moduleId}/{trainerId}")
	public Map<String, Boolean> deleteAssignment(
			@PathVariable(value = "classId") Class classId,
			@PathVariable(value = "moduleId") Module moduleId, @PathVariable(value = "trainerId") Trainer trainerId)
			throws ResourceNotFoundException {
		
		AssignmentKey asmKey = new AssignmentKey(classId, moduleId, trainerId);
		
		Assignment asm = asmRepository.findById(asmKey)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment Not Found"));
		
		asmRepository.delete(asm);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}