package org.sree.studentnetflixoss.student.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sree.studentnetflixoss.student.dao.StudentDAOService;
import org.sree.studentnetflixoss.student.model.Parent;
import org.sree.studentnetflixoss.student.model.Student;

@Component
public class UserService {
	
	
	private List<Parent> users = new ArrayList();
	
	@Autowired
	private StudentDAOService daoService;
	
	// get all parents
	public List<Parent> retreiveAllUsers(){
		return daoService.retreiveAllUsers();
		
	}
	
	// get parent with id
	public Parent retreiveUser(Long userId){
		return daoService.retreiveUser(userId);
		
	}
	
	// save new parent to DB
	public Parent saveUser(Parent parent){
		return daoService.saveUser(parent);
		
	}
	
	// update parent to DB
	public Parent updateUser(Long parentId, Parent parent){
		return daoService.updateUser(parentId, parent);
		
	}
	
	// save new parent to DB
	public void deleteUser(Long parentId){
		daoService.deleteUser(parentId);
		
	}

	// get all students with parentid
	public List<Student> retreiveAllStudentsByParent(Parent parent){
		return daoService.retreiveAllStudentsByParent(parent);
		
	}
	
	// save new Student to DB
	public Student saveStudent(Student student){
		return daoService.saveStudent(student);
		
	}
	

	// save new parent to DB
	public void deleteStudent(Long studentId){
		daoService.deleteStudent(studentId);
		
	}	
	
}
