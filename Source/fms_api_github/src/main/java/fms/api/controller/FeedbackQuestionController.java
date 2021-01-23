package fms.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fms.api.dto.Feedback_Question_DTO;
import fms.api.entity.Feedback;
import fms.api.entity.Feedback_Question;
import fms.api.entity.Feedback_QuestionKey;
import fms.api.entity.Question;
import fms.api.exception.ResourceNotFoundException;
import fms.api.repository.FeedbackQuestionRepository;
import fms.api.repository.FeedbackRepository;
import fms.api.repository.QuestionRepository;

@RestController
@RequestMapping("/api/v1")
public class FeedbackQuestionController {
	@Autowired
	private FeedbackQuestionRepository repository;
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired 
	private QuestionRepository questionRepository;
	
	@GetMapping("/FeedbackQuestions")
	public List<Feedback_Question> getAllFeedbackQuestions() {
		List<Feedback_Question> feedback_Questions = repository.findAll();
//		Feedback_QuestionKey feedback_QuestionKey = new Feedback_QuestionKey();
//		List<Feedback_Question_DTO> 
		return feedback_Questions;
	}
	
	@GetMapping("/FeedbackQuestions/{id}")
	public List<Feedback_Question> getFeedbackQuestionById(@PathVariable(value = "id") Long feedbackQuestionId) 
			throws ResourceNotFoundException {
//		Feedback_Question feedback_Question = repository.findbyFeedbackID(feedbackQuestionId)
//				.orElseThrow(()->new ResourceNotFoundException("Feedback not found on :: " + feedbackQuestionId));
//		return ResponseEntity.ok().body(feedback_Question);
		return repository.findbyFeedbackID(feedbackQuestionId);

	}
	
//	@RequestMapping(path = "/FeedbackQuestions/{feedbackID}/{questionID}", method = RequestMethod.POST)
//	public void addFeedbackQuestion(@PathVariable(name = "feedbackID") Long feedbackID, @PathVariable(name = "questionID") Long questionID) {
//		repository.insertFeedbackDetail(feedbackID, questionID);
//	}
	
	@PostMapping("/FeedbackQuestions")
	public Feedback_Question addFeedback_Question(@RequestBody Feedback_Question_DTO feedback_Question_DTO) throws ResourceNotFoundException {
		Feedback_Question feedback_Question = new Feedback_Question();
		Feedback feedback = feedbackRepository.findById(feedback_Question_DTO.getFeedbackID()).orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));
		Question question = questionRepository.findById(feedback_Question_DTO.getQuestionID()).orElseThrow(() -> new ResourceNotFoundException("Question not found"));

		feedback_Question.setFeedback_QuestionKey(new Feedback_QuestionKey(feedback, question));
		return repository.save(feedback_Question);
	}
	
	@DeleteMapping("/FeedbackQuestions/{id}/{questionid}")
	public Map<String, Boolean> deleteFeedback_Question(@PathVariable(value = "id") Long feedbackId, @PathVariable(value = "questionid") Long questionId) throws ResourceNotFoundException {
		Feedback_Question feedback = repository.findbyQuestionID(feedbackId, questionId);
		repository.delete(feedback);
		Map<String, Boolean> response = new HashMap<>();
		response.put("delete", Boolean.TRUE);
		return response;
	}
	
	@Transactional
	@PostMapping("/FeedbackQuestions/{id}/{questionid}")
	public void insertFeedback_Question(@PathVariable(value = "id") Long feedbackId, @PathVariable(value = "questionid") Long questionId) throws ResourceNotFoundException {
		repository.insertFeedbackDetail(feedbackId, questionId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
