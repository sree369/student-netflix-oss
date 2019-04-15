package org.sree.studentnetflixoss.marks.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.sree.studentnetflixoss.marks.model.Marks;
import org.sree.studentnetflixoss.marks.repository.MarksRepository;
import org.sree.studentnetflixoss.marks.service.MarksService;

@RestController
public class MarksController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MarksService service;
	
	@Autowired
	private Environment environment;
	
	// /marks/students/{studentid}/subjects/{subjectid}/terms/{termtype}
	@GetMapping("/marks/students/{studentid}/subjects/{subjectid}/terms/{termtype}")
	public Marks retreiveTermMarks(@PathVariable String studentid, @PathVariable String subjectid,
			@PathVariable String termtype){
		
		Marks marks = service.getTermMarks(studentid, subjectid, termtype);
		marks.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		logger.info("{Marks value returned }", marks);
		
		return marks;
	}
	
	
	// /marks-service/users/{userid}/students/{studentid}/terms/{terms_type}
	
	public List<Marks> retreiveTermTypeMarks(){
		return null;
	}
}
