package demo.springboot.redis.cache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import demo.springboot.redis.cache.model.Student;
import demo.springboot.redis.cache.repo.StudentRepository;

@RestController
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	@PostMapping("/student")
	public void addStudent(@RequestBody Student student) {
		
		studentRepository.save(student);
	}
	
	@GetMapping("/student")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	@Cacheable(value = "studentCache", key = "#id")
	@GetMapping("/student/{id}")
	public Student getStudent(@PathVariable String id){
		return studentRepository.findById(id).orElse(null);
	}
	
	@PutMapping("/student")
	public void updateStudent(@RequestBody Student student) {
		
		studentRepository.save(student);
	}
	
	@CacheEvict(value = "studentCache", key = "#id")
	@DeleteMapping("/student/{id}")
	public void deleteStudent(@PathVariable String id){
		studentRepository.deleteById(id);
	}
}
