package fms.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fms.api.entity.Topic;
import fms.api.exception.ResourceNotFoundException;
import fms.api.repository.TopicRepository;

@RestController
@RequestMapping("/api/v1")
public class TopicController {

	@Autowired
	private TopicRepository topicRepository;
	@GetMapping("/topic")
	public List<Topic> getAllTopic() {
		return topicRepository.findAll();
	}
		
	@GetMapping("/topic/{id}")
	public ResponseEntity<Topic> getTopicById(@PathVariable(value = "id") Long topicId)
		throws ResourceNotFoundException {
		Topic topic =
				topicRepository
					.findById(topicId)
					.orElseThrow(() -> new ResourceNotFoundException("Topic not found on :: " + topicId));
		
		return ResponseEntity.ok().body(topic);
	}
	@PostMapping("/topic")
	public Topic createTopic(@Validated @RequestBody Topic topic) {
		return topicRepository.save(topic);
	}
	@PutMapping("/topic/{id}")
	public ResponseEntity<Topic> updateTopic(@PathVariable(value = "id") Long topicId
			,@Validated @RequestBody Topic topicDetails) throws ResourceNotFoundException {
		
		Topic topic = 
				topicRepository
					.findById(topicId)
					.orElseThrow(() -> new ResourceNotFoundException("Topic not found on :: " + topicId));
		topic.setTopicID(topicDetails.getTopicID());
		topic.setTopicName(topicDetails.getTopicName());
		topic.setQuestions(topicDetails.getQuestions());
		
		final Topic updateTopic = topicRepository.save(topic);
		
		return ResponseEntity.ok(updateTopic);
	}
	public Map<String, Boolean> deleteTopic(@PathVariable(value = "id") Long topicId) throws Exception{
		Topic topic =
			topicRepository
				.findById(topicId)
				.orElseThrow(() -> new ResourceNotFoundException("Topic not found on :: " + topicId));
		
		topicRepository.delete(topic);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
	
}






















