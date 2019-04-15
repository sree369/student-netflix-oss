package org.sree.studentnetflixoss.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.sree.studentnetflixoss.student.model.Parent;
import org.sree.studentnetflixoss.student.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	// /users
	@GetMapping("/users")
	public List<Parent> getAllUsers(){
		return userService.retreiveAllUsers();
	}
	
	
	// /users/{userid}
	
	@GetMapping("/users/{userId}")
	public Parent retrieveUser(@PathVariable String userId) {
		return userService.retreiveUser(Long.parseLong(userId));
	}
	
	// /users/{userid}/students
	//TODO
	
	// /users/{userid}/students/{studentid}
	// TODO
}
