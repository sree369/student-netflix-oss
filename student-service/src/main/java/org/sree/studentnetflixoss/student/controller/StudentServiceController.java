package org.sree.studentnetflixoss.student.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.sree.studentnetflixoss.student.exception.ParentNotFoundException;
import org.sree.studentnetflixoss.student.exception.StudentNotFoundException;
import org.sree.studentnetflixoss.student.model.Parent;
import org.sree.studentnetflixoss.student.model.Student;
import org.sree.studentnetflixoss.student.service.UserService;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class StudentServiceController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	// GET /students-service/parents
	@GetMapping("/students-service/parents")
	public List<Parent> getAllUsers(){
		return userService.retreiveAllUsers();
	}
	
	// POST /students-service/parents
	@PostMapping("/students-service/parents")
	public ResponseEntity<Object> saveNewUser(@RequestBody Parent parent){
		Parent newParent = userService.saveUser(parent);
		// CREATED
		// /parent/{parentId}     newParent.getParentId()
		// HATEOS
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{parentId}")
			.buildAndExpand(newParent.getParentId()).toUri();
		
		return ResponseEntity.created(location).build();		
	}
	
	// GET /students-service/parents/{parentId}
	@GetMapping("/students-service/parents/{parentId}")
	public Parent retrieveUser(@PathVariable String parentId) {
		Parent parent = userService.retreiveUser(Long.parseLong(parentId));
		if(parent == null)
			throw new ParentNotFoundException("Parent not found with id "+ parentId);
		
		return parent;
	}
	
	// PUT /students-service/parents/{parentId}
	@PutMapping("/students-service/parents/{parentId}")
	public Parent updateUser(@PathVariable String parentId, @RequestBody Parent parent) {
		Parent updatedParent = userService.updateUser(Long.parseLong(parentId), parent);
		if(updatedParent == null)
			throw new ParentNotFoundException("Parent not found with id "+ parentId);
		
		return parent;
	}
	
	// DELETE /students-service/parents/{parentId}
	@DeleteMapping("/students-service/parents/{parentId}")
	public void deleteUser(@PathVariable String parentId) {
		if(parentId == null)
			throw new ParentNotFoundException("Parent not found with id "+ parentId);
		
		userService.deleteUser(Long.parseLong(parentId));
	}
	
	
	
	// GET /students-service/parents/{parentId}/students
	@GetMapping("/students-service/parents/{parentId}/students")
	public List<Student> getAllStudents(@PathVariable String parentId){
		Parent parent = userService.retreiveUser(Long.parseLong(parentId));
		if(parent == null) {
			throw new ParentNotFoundException("Parent not found with id " + parentId);
		}
		return parent.getStudents();
		

	}
	
	// POST /students-service/parents/{parentId}/students
	@PostMapping("/students-service/parents/{parentId}/students")
	public Student saveStudentForparentId(@PathVariable String parentId, @RequestBody Student student){
		Parent parent = userService.retreiveUser(Long.parseLong(parentId));
		if(parent == null) {
			throw new ParentNotFoundException("Parent not found with id " + parentId);
		}
//		parent.getStudents().add(student);
		
		student.setParent(parent);
		userService.saveStudent(student);
		userService.saveUser(parent);
		return student;
		

	}
	
	// GET /students-service/parents/{parentId}/students/{studentid}
	@GetMapping("/students-service/parents/{parentId}/students/{studentId}")
	public Resource<Student> getStudent(@PathVariable String parentId, @PathVariable String studentId){
		Parent parent = userService.retreiveUser(Long.parseLong(parentId));
		if(parent == null) {
			throw new ParentNotFoundException("Parent not found with id " + parentId);
		}
		log.info("Parent found : "+ parent.toString());
		List<Student> students =  parent.getStudents();//userService.retreiveStudent(Long.parseLong(studentId));
		Student selectedStudent = null;
		for(Student student: students) {
			if(student.getStudentId() == Long.parseLong(studentId)) {
				selectedStudent = student;
				break;
			}
		}
		log.info("Student found : "+ selectedStudent.toString());
		if(selectedStudent == null) {
			throw new StudentNotFoundException("Student not found with id " + studentId);
		}
		//"all-students", SERVER_PATH + "/students-service/parents/{parentId}/students"
		//getAllStudents
		Resource<Student> resource = new Resource<Student>(selectedStudent);
		
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).getAllStudents(String.valueOf(parent.getParentId())));
		
		resource.add(linkTo.withRel("all-students"));	
		//HATEOAS	
		return resource;
	}
	
	// PUT /students-service/parents/{parentId}/students/{studentid}
	@PutMapping("/students-service/parents/{parentId}/students/{studentId}")
	public Student updateStudent(@PathVariable String parentId, @PathVariable String studentId, @RequestBody Student reqstStudent){
		Parent parent = userService.retreiveUser(Long.parseLong(parentId));
		if(parent == null) {
			throw new ParentNotFoundException("Parent not found with id " + parentId);
		}
		log.info("Parent found : "+ parent.toString());
		List<Student> students =  parent.getStudents();//userService.retreiveStudent(Long.parseLong(studentId));
		Student selectedStudent = null;
		for(Student student: students) {
			if(student.getStudentId() == Long.parseLong(studentId)) {
				selectedStudent = student;
				break;
			}
		}
		log.info("Student found : "+ selectedStudent.toString());
		if(selectedStudent == null) {
			throw new StudentNotFoundException("Student not found with id " + studentId);
		}
		reqstStudent.setParent(parent);
		reqstStudent.setStudentId(selectedStudent.getStudentId());
		Student savedStudent = userService.saveStudent(reqstStudent);
		return savedStudent;
	}
	
	// DELETE /students-service/parents/{parentId}/students/{studentid}
	@DeleteMapping("/students-service/parents/{parentId}/students/{studentId}")
	public Student updateStudent(@PathVariable String parentId, @PathVariable String studentId){
		Parent parent = userService.retreiveUser(Long.parseLong(parentId));
		if(parent == null) {
			throw new ParentNotFoundException("Parent not found with id " + parentId);
		}
		log.info("Parent found : "+ parent.toString());
		List<Student> students =  parent.getStudents();//userService.retreiveStudent(Long.parseLong(studentId));
		Student selectedStudent = null;
		for(Student student: students) {
			if(student.getStudentId() == Long.parseLong(studentId)) {
				selectedStudent = student;
				break;
			}
		}
		log.info("Student found : "+ selectedStudent.toString());
		if(selectedStudent == null) {
			throw new StudentNotFoundException("Student not found with id " + studentId);
		}
		students.remove(selectedStudent);
		parent.setStudents(students);
		userService.saveUser(parent);
		userService.deleteStudent(Long.parseLong(studentId));
		return selectedStudent;
	}
	
	
	// /students-service/parents/{parentId}/students/{studentid}/subjects
}
