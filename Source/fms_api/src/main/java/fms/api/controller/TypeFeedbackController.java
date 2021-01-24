package fms.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fms.api.entity.Feedback;
import fms.api.entity.TypeFeedback;
import fms.api.repository.TypeFeedbackRepository;

@RestController
@RequestMapping("/api/v1")
public class TypeFeedbackController {
	
	@Autowired
	private TypeFeedbackRepository repository;
	
	@GetMapping("/TypeFeedback")
	public List<TypeFeedback> getAllFeedbacks() {
		return repository.findAll();
	}
}
