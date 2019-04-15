package org.sree.studentnetflixoss.student.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sree.studentnetflixoss.student.dao.StudentDAOService;
import org.sree.studentnetflixoss.student.model.Parent;

@Component
public class UserService {
	
	
	private List<Parent> users = new ArrayList();
	
	@Autowired
	private StudentDAOService daoService;
	
	public List<Parent> retreiveAllUsers(){
		return daoService.getAllUsers();
		
	}
	
	public Parent retreiveUser(long userId){
		return daoService.getUser(userId);
		
	}

}
