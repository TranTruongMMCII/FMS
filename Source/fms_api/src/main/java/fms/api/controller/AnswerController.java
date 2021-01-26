package fms.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fms.api.dto.AnswerDTO;
import fms.api.entity.Answer;
import fms.api.entity.AnswerKey;
import fms.api.entity.Class;
import fms.api.entity.Module;
import fms.api.entity.Question;
import fms.api.entity.Trainee;
import fms.api.exception.ResourceNotFoundException;
import fms.api.repository.AnswerRepository;
import fms.api.repository.ClassRepository;
import fms.api.repository.ModuleRepository;
import fms.api.repository.QuestionRepository;
import fms.api.repository.TraineeRepository;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {
	@Autowired
	AnswerRepository answerRepository;
	
	@Autowired
	ClassRepository classRepository;
	
	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	TraineeRepository traineeRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@GetMapping("/getAll")
	public List<AnswerDTO> getAllAnswer(){
		List<Answer> answers = answerRepository.findAll();
		AnswerKey answerKey = new AnswerKey();
		List<AnswerDTO> answerDTOs = new ArrayList<AnswerDTO>();
		
		for (Answer answer : answers) {
			answerKey = answer.getAnswerKey();
			AnswerDTO answerDTO = new AnswerDTO();
			
			Class class1 = answerKey.getClassID();
			answerDTO.setClassID(class1.getClassID());
			
			Module module = answerKey.getModuleID();
			answerDTO.setModuleID(module.getModuleID());
			
			Trainee trainee = answerKey.getTraineeID();
			answerDTO.setTraineeID(trainee.getUserName());
			
			Question question = answerKey.getQuestionID();
			answerDTO.setQuestionID(question.getQuestionID());
			
			answerDTO.setValue(answer.getValue());
			
			answerDTOs.add(answerDTO);
		}
		return answerDTOs;
	}
	
	@GetMapping("/getAnswerById/{classID}/{moduleID}/{traineeID}/{questionID}")
	public ResponseEntity<AnswerDTO> getAnswerById(
			@PathVariable(value = "classID") Class classID,
			@PathVariable(value = "moduleID") Module moduleID,
			@PathVariable(value = "traineeID") Trainee traineeID,
			@PathVariable(value = "questionID") Question questionID) throws ResourceNotFoundException{
		
		AnswerKey answerKey = new AnswerKey(classID, moduleID, traineeID, questionID);
//		Answer answer = answerRepository.findAnswerById(answerKey)
//				.orElseThrow(()->new ResourceNotFoundException("Answer not found!"));

		Answer answer = answerRepository.findById(answerKey)
				.orElseThrow(()->new ResourceNotFoundException("Answer not found!"));
		AnswerDTO answerDTO = new AnswerDTO();
		
		Class class1 = answerKey.getClassID();
		answerDTO.setClassID(class1.getClassID());
		
		Module module = answerKey.getModuleID();
		answerDTO.setModuleID(module.getModuleID());
		
		Trainee trainee = answerKey.getTraineeID();
		answerDTO.setTraineeID(trainee.getUserName());
		
		Question question = answerKey.getQuestionID();
		answerDTO.setQuestionID(question.getQuestionID());
		
		answerDTO.setValue(answer.getValue());
		
		return ResponseEntity.ok().body(answerDTO);
	}
	
	@GetMapping("/getAnswerByClassId/{classID}")
	public List<AnswerDTO> getAnswerByClassId(
			@PathVariable(value = "classID") int classID) throws ResourceNotFoundException{
		List<Answer> answers = answerRepository.findAnswerByClassId(classID);
		AnswerKey answerKey = new AnswerKey();
		List<AnswerDTO> answerDTOs = new ArrayList<AnswerDTO>();
		
		for (Answer answer : answers) {
			answerKey = answer.getAnswerKey();
			AnswerDTO answerDTO = new AnswerDTO();
			
			Class class1 = answerKey.getClassID();
			answerDTO.setClassID(class1.getClassID());
			
			Module module = answerKey.getModuleID();
			answerDTO.setModuleID(module.getModuleID());
			
			Trainee trainee = answerKey.getTraineeID();
			answerDTO.setTraineeID(trainee.getUserName());
			
			Question question = answerKey.getQuestionID();
			answerDTO.setQuestionID(question.getQuestionID());
			
			answerDTO.setValue(answer.getValue());
			
			answerDTOs.add(answerDTO);
		}
		return answerDTOs;
	}
	
	@GetMapping("/getAnswerByModuleId/{moduleID}")
	public List<AnswerDTO> getAnswerByModuleId(
			@PathVariable(value = "moduleID") int moduleID) throws ResourceNotFoundException{
		List<Answer> answers = answerRepository.findAnswerByModuleId(moduleID);
		AnswerKey answerKey = new AnswerKey();
		List<AnswerDTO> answerDTOs = new ArrayList<AnswerDTO>();
		
		for (Answer answer : answers) {
			answerKey = answer.getAnswerKey();
			AnswerDTO answerDTO = new AnswerDTO();
			
			Class class1 = answerKey.getClassID();
			answerDTO.setClassID(class1.getClassID());
			
			Module module = answerKey.getModuleID();
			answerDTO.setModuleID(module.getModuleID());
			
			Trainee trainee = answerKey.getTraineeID();
			answerDTO.setTraineeID(trainee.getUserName());
			
			Question question = answerKey.getQuestionID();
			answerDTO.setQuestionID(question.getQuestionID());
			
			answerDTO.setValue(answer.getValue());
			
			answerDTOs.add(answerDTO);
		}
		return answerDTOs;
	}
	
	@GetMapping("/getAnswerByTraineeId/{traineeID}")
	public List<AnswerDTO> getAnswerByTraineeId(
			@PathVariable(value = "traineeID") String traineeID) throws ResourceNotFoundException{
		List<Answer> answers = answerRepository.findAnswerByTraineeId(traineeID);
		AnswerKey answerKey = new AnswerKey();
		List<AnswerDTO> answerDTOs = new ArrayList<AnswerDTO>();
		
		for (Answer answer : answers) {
			answerKey = answer.getAnswerKey();
			AnswerDTO answerDTO = new AnswerDTO();
			
			Class class1 = answerKey.getClassID();
			answerDTO.setClassID(class1.getClassID());
			
			Module module = answerKey.getModuleID();
			answerDTO.setModuleID(module.getModuleID());
			
			Trainee trainee = answerKey.getTraineeID();
			answerDTO.setTraineeID(trainee.getUserName());
			
			Question question = answerKey.getQuestionID();
			answerDTO.setQuestionID(question.getQuestionID());
			
			answerDTO.setValue(answer.getValue());
			
			answerDTOs.add(answerDTO);
		}
		return answerDTOs;
	}
	
	@GetMapping("/getAnswerByQuestionId/{questionID}")
	public List<AnswerDTO> getAnswerByQuestionId(
			@PathVariable(value = "questionID") int questionID) throws ResourceNotFoundException{
		List<Answer> answers = answerRepository.findAnswerByQuestionId(questionID);
		AnswerKey answerKey = new AnswerKey();
		List<AnswerDTO> answerDTOs = new ArrayList<AnswerDTO>();
		
		for (Answer answer : answers) {
			answerKey = answer.getAnswerKey();
			AnswerDTO answerDTO = new AnswerDTO();
			
			Class class1 = answerKey.getClassID();
			answerDTO.setClassID(class1.getClassID());
			
			Module module = answerKey.getModuleID();
			answerDTO.setModuleID(module.getModuleID());
			
			Trainee trainee = answerKey.getTraineeID();
			answerDTO.setTraineeID(trainee.getUserName());
			
			Question question = answerKey.getQuestionID();
			answerDTO.setQuestionID(question.getQuestionID());
			
			answerDTO.setValue(answer.getValue());
			
			answerDTOs.add(answerDTO);
		}
		return answerDTOs;
	}
	
	@GetMapping("/getAnswerByValue/{value}")
	public List<AnswerDTO> getAnswerByValue(
			@PathVariable(value = "value") Integer value) throws ResourceNotFoundException{
		List<Answer> answers = answerRepository.findAnswerByValue(value);
		AnswerKey answerKey = new AnswerKey();
		List<AnswerDTO> answerDTOs = new ArrayList<AnswerDTO>();
		
		for (Answer answer : answers) {
			answerKey = answer.getAnswerKey();
			AnswerDTO answerDTO = new AnswerDTO();
			
			Class class1 = answerKey.getClassID();
			answerDTO.setClassID(class1.getClassID());
			
			Module module = answerKey.getModuleID();
			answerDTO.setModuleID(module.getModuleID());
			
			Trainee trainee = answerKey.getTraineeID();
			answerDTO.setTraineeID(trainee.getUserName());
			
			Question question = answerKey.getQuestionID();
			answerDTO.setQuestionID(question.getQuestionID());
			
			answerDTO.setValue(value);
			
			answerDTOs.add(answerDTO);
		}
		return answerDTOs;
	}
	
	@PostMapping("/addAnswer")
	public Answer addAnswer(@RequestBody AnswerDTO answerDTO) throws ResourceNotFoundException{

		Answer answer = new Answer();
		
		Class class1 = classRepository.findById((long) answerDTO.getClassID())
				.orElseThrow(()->new ResourceNotFoundException("Class not found!"));
		
		Module module = moduleRepository.findById(answerDTO.getModuleID())
				.orElseThrow(()->new ResourceNotFoundException("Module not found!"));
		
		Trainee trainee = traineeRepository.findById(answerDTO.getTraineeID())
				.orElseThrow(()->new ResourceNotFoundException("Trainee not found!"));
		
		Question question = questionRepository.findById(answerDTO.getQuestionID())
				.orElseThrow(()->new ResourceNotFoundException("Question not found!"));
		
		answer.setAnswerKey(new AnswerKey(class1, module, trainee, question));
		answer.setValue(answerDTO.getValue());
		answer.setComment(answerDTO.getComment());
		
		return answerRepository.save(answer);
	}
	
	@DeleteMapping("/deleteAnswer/{classID}/{moduleID}/{traineeID}/{questionID}")
	public Map<String, Boolean> deleteAnswer(
			@PathVariable(value = "classID") Class classID,
			@PathVariable(value = "moduleID") Module moduleID,
			@PathVariable(value = "traineeID") Trainee traineeID,
			@PathVariable(value = "questionID") Question questionID) throws ResourceNotFoundException{
		Answer answer = answerRepository.findById(new AnswerKey(classID, moduleID, traineeID, questionID))
				.orElseThrow(()->new ResourceNotFoundException("Answer not found!"));
		
		answerRepository.delete(answer);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}

