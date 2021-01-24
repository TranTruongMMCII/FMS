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

import fms.api.entity.*;
import fms.api.exception.ResourceNotFoundException;
import fms.api.repository.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserRepository repository;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return repository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
		throws ResourceNotFoundException {
		User user = repository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User not found on :: " + userId));
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("/users")
	public User addUser(@Validated
						@RequestBody User user	) {
		return repository.save(user);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @Validated @RequestBody User userDetail)
	throws ResourceNotFoundException{
			User user = repository.findById(userId)
			.orElseThrow(()->new ResourceNotFoundException("User not found on :: " + userId));
			user.setEmail(userDetail.getEmail());
			user.setFirstName(userDetail.getFirstName());
			user.setLastName(userDetail.getLastName());
			
			final User user2 = repository.save(user);
			return ResponseEntity.ok(user2);
}
	
	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deletUser(@PathVariable(value = "id" )Long userID) throws Exception{
		User user = repository.findById(userID).orElseThrow(()-> new ResourceNotFoundException("User not found on :: " + userID));
		repository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("delete", Boolean.TRUE);
		return response;
	}
}