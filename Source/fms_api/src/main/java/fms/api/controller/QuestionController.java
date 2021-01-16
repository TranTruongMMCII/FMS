package fms.api.controller;

import org.springframework.web.bind.annotation.RestController;

import fms.api.entity.Question;
import fms.api.exception.ResourceNotFoundException;
import fms.api.repository.QuestionRepository;

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

@RestController
@RequestMapping("/api/v1")

public class QuestionController {
	@Autowired
	private QuestionRepository questionRespository;
	
	@GetMapping("/questions")
	public List<Question> getAllQuestions()
	{
		return questionRespository.findAll();
		
	}
	
	@GetMapping("/questions/{id}")
	public ResponseEntity<Question> getCourseById(@PathVariable(value = "id") Long questionId)
		throws ResourceNotFoundException{
		Question question = questionRespository.findById(questionId).orElseThrow(()->new ResourceNotFoundException("Question not found on :: " + questionId));
				
		return ResponseEntity.ok().body(question);
	}
	
	@PostMapping("/questions")
	public Question createQuestion(@Validated @RequestBody Question question) {
		return questionRespository.save(question);
	}

	@PutMapping("/questions/{id}")
	public ResponseEntity<Question> updateQuestion(@PathVariable (value = "id") Long questionId, @Validated @RequestBody Question questionDetails) throws ResourceNotFoundException {
		
		Question question = questionRespository.findById(questionId).orElseThrow(()->new ResourceNotFoundException("Question not found on :: " + questionId) );
		
		question.setAnswers(questionDetails.getAnswers());
		question.setFeedback_Questions(questionDetails.getFeedback_Questions());
		question.setIsDeleted(questionDetails.getIsDeleted());
		question.setQuesionContent(questionDetails.getQuesionContent());
		question.setQuestionID(questionDetails.getQuestionID());
		question.setTopic_question(questionDetails.getTopic_question());
		
		final Question updateQuestion = questionRespository.save(question);
		return ResponseEntity.ok(updateQuestion);
	}
	
	@DeleteMapping("/questions/{id}")
	public Map<String, Boolean> deleteQuestion(@PathVariable(value = "id") Long questionId) throws Exception 
	{
		Question question = questionRespository.findById(questionId).orElseThrow(()->new ResourceNotFoundException("Question not found on :: " + questionId));
		
		questionRespository.delete(question);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
		
	}

}
