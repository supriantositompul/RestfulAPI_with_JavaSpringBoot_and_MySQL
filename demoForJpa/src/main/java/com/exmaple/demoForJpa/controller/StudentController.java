package com.exmaple.demoForJpa.controller;

import com.exmaple.demoForJpa.entity.Student;
import com.exmaple.demoForJpa.entity.Course;
import com.exmaple.demoForJpa.repo.CourseRepo;
import com.exmaple.demoForJpa.repo.StudentRepo;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@Autowired
	StudentRepo studentRepo;
	@Autowired
	CourseRepo courseRepo;
	
	//POST
	@PostMapping("/api/students")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		return new ResponseEntity<>(studentRepo.save(student), HttpStatus.CREATED);
		
	}
	//GET
	@GetMapping("/api/students")
	public ResponseEntity<List<Student>> getStudents() {
		return new ResponseEntity<>(studentRepo.findAll(), HttpStatus.OK);
	}
	
	//GET BY ID
	@GetMapping("/api/students/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable long id){
		Optional<Student> student = studentRepo.findById(id);
		if (student.isPresent()) {
			return new ResponseEntity<>(student.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		}
	}
	//PUT
	@PutMapping("/api/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable long id, @RequestBody Student stud){
		Optional<Student> student = studentRepo.findById(id);
		if (student.isPresent()) {
			student.get().setStudentName(stud.getStudentName());
			student.get().setStudentEmail(stud.getStudentEmail());
			student.get().setStudentAddress(stud.getStudentAddress());
			return new ResponseEntity<>(studentRepo.save(student.get()), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		}
	}
	//DELETE
		@DeleteMapping("/api/students/{id}")
		public ResponseEntity<Void> deleteStudent(@PathVariable long id, @RequestBody Student stud){
			Optional<Student> student = studentRepo.findById(id);
			if (student.isPresent()) {
				studentRepo.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
			}
		}		
		@PostMapping("/api/courses")
		public ResponseEntity<Course> saveCourse(@RequestBody Course course) {
			return new ResponseEntity<>(courseRepo.save(course), HttpStatus.CREATED);
		}
		@GetMapping("/api/courses")
		public ResponseEntity<List<Course>> getCourses() {
			return new ResponseEntity<>(courseRepo.findAll(), HttpStatus.OK);
		}
		@GetMapping("/api/courses/{id}")
		public ResponseEntity<Course> getCourse(@PathVariable long id) {
			Optional<Course> course = courseRepo.findById(id);
			if (course.isPresent()) {
				return new ResponseEntity<>(course.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		@PutMapping("/api/courses/{id}")
		public ResponseEntity<Course> updateCourse(@PathVariable long id, @RequestBody Course updatedCourse) {
			Optional<Course> course = courseRepo.findById(id);
			if (course.isPresent()) {
				// Update the course properties with values from updatedCourse
				Course existingCourse = course.get();
				existingCourse.setCourseName(updatedCourse.getCourseName());
				// Update other properties as needed
				return new ResponseEntity<>(courseRepo.save(existingCourse), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		@DeleteMapping("/api/courses/{id}")
		public ResponseEntity<Void> deleteCourse(@PathVariable long id) {
			Optional<Course> course = courseRepo.findById(id);
			if (course.isPresent()) {
				courseRepo.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
}
