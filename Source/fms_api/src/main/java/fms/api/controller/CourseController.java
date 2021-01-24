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

import fms.api.entity.Course;
import fms.api.exception.ResourceNotFoundException;
import fms.api.repository.CourseRepository;

@RestController
@RequestMapping("/api/v1")
public class CourseController {

	@Autowired
	private CourseRepository repository;
	
	@GetMapping("/courses")
	public List<Course> getAllCourses(){
		return repository.findAll();
	}
	
	@GetMapping("/courses/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable(value = "id") Long courseId)
		throws ResourceNotFoundException{
		Course course = repository.findById(courseId)
				.orElseThrow(()->new ResourceNotFoundException("Course not found on :: " + courseId));
		return ResponseEntity.ok().body(course);
	}
	
	@PostMapping("/courses")
	public Course addCourse(@Validated
						@RequestBody Course course	) {
		return repository.save(course);
	}
	
	@PutMapping("/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable(value = "id") Long courseId, @Validated @RequestBody Course courseDetail)
	throws ResourceNotFoundException{
			Course course = repository.findById(courseId)
			.orElseThrow(()->new ResourceNotFoundException("Course not found on :: " + courseId));
			course.setAdmin(courseDetail.getAdmin());
			course.setCourseName(courseDetail.getCourseName());
			course.setStartDate(courseDetail.getStartDate());
			course.setEndDate(courseDetail.getEndDate());
			
			final Course course2 = repository.save(course);
			return ResponseEntity.ok(course2);
	}
	
	@DeleteMapping("/courses/{id}")
	public Map<String, Boolean> deleteCourse(@PathVariable(value = "id" )Long courseId) throws Exception{
		Course course = repository.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("Course not found on :: " + courseId));
		repository.delete(course);
		Map<String, Boolean> response = new HashMap<>();
		response.put("delete", Boolean.TRUE);
		return response;
	}
}
