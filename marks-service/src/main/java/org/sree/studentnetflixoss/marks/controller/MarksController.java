package org.sree.studentnetflixoss.marks.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.sree.studentnetflixoss.marks.exception.MarksNotFoundException;
import org.sree.studentnetflixoss.marks.model.GradeBean;
import org.sree.studentnetflixoss.marks.model.Marks;
import org.sree.studentnetflixoss.marks.proxy.GradationProxy;
import org.sree.studentnetflixoss.marks.repository.MarksRepository;
import org.sree.studentnetflixoss.marks.service.MarksService;


@RestController
public class MarksController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MarksService service;
	
	@Autowired
	private Environment environment;
	@Autowired	
	private GradationProxy proxy;
	
	// GET /marks/students/{studentid}/subjects/{subjectid}/terms
	@GetMapping("/marks/students/{studentid}/subjects/{subjectid}/terms")
	public List<Marks> retreiveAllTermMarks(@PathVariable String studentid, @PathVariable String subjectid){
		
		List<Marks> marksList = service.getAllTermMarks(Long.parseLong(studentid), Long.parseLong(subjectid));
		if(marksList.isEmpty()) {
			throw new MarksNotFoundException("No Marks found for the studentid : " + studentid +  " and subjectid : "+ subjectid);
		}
		for(Marks mark: marksList) {
			mark.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		}
		
		logger.info("{List of Marks returned }");
		
		return marksList;
	}
	
	// POST /marks/students/{studentid}/subjects/{subjectid}/terms
	@PostMapping("/marks/students/{studentid}/subjects/{subjectid}/terms")
	public Resource<Marks> saveMarksByTermType(@PathVariable String studentid, @PathVariable String subjectid, 
			@Valid @RequestBody Marks marks){	
		service.saveMarksByTermType(Long.parseLong(studentid), Long.parseLong(subjectid), marks);
		
		//"all-marks", SERVER_PATH + "/marks/students/{studentid}/subjects/{subjectid}/terms"
		//retrieveAllSubjects
		Resource<Marks> resource = new Resource<Marks>(marks);
		
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retreiveAllTermMarks(studentid, subjectid));
		
		resource.add(linkTo.withRel("all-marks"));
		
		//HATEOAS
		
		return resource;
	}
	
	// GET /marks/students/{studentid}/subjects/{subjectid}/terms/{termtype}
	@GetMapping("/marks/students/{studentid}/subjects/{subjectid}/terms/{termtype}")
	public Resource<Marks> retreiveTermMarks(@PathVariable String studentid, @PathVariable String subjectid,
			@PathVariable String termtype){
		
		Marks marks = service.getTermMarks(studentid, subjectid, termtype);
		if(marks == null) {
			throw new MarksNotFoundException("No Marks found for the studentid : " + studentid +  " and subjectid : "+ subjectid + 
					" and TermType : "+ termtype);
		}
		marks.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		logger.info("{Marks value returned }"+ marks);
		
		//"all-marks", SERVER_PATH + "/marks/students/{studentid}/subjects/{subjectid}/terms"
		//retrieveAllSubjects
		Resource<Marks> resource = new Resource<Marks>(marks);
		
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retreiveAllTermMarks(studentid, subjectid));
		
		resource.add(linkTo.withRel("all-marks"));
		
		//HATEOAS
		
		return resource;
//		return marks;
	}
	
	// GET /marks/students/{studentid}/subjects/{subjectid}/terms/{termtype}
	@GetMapping("/marks/students/{studentid}/classnos/{classno}/subjects/{subjectid}/outofmarks/{outofmarks}/terms/{termtype}/gradation")
	public GradeBean retreiveTermMarks(@PathVariable String studentid, @PathVariable BigDecimal classno, 
			@PathVariable String subjectid,	@PathVariable Integer outofmarks, @PathVariable String termtype){
		
		Marks marks = service.getTermMarks(studentid,classno.intValue(), subjectid, termtype);
		logger.info("{Marks }"+ marks);
		if(marks == null) {
			throw new MarksNotFoundException("No Marks found for the studentid : " + studentid +  " and classno "+ classno + "and subjectid : "+ subjectid + 
					" and TermType : "+ termtype);
		}
		double markOfSubject = Integer.valueOf(marks.getObtainedMarks()).doubleValue();
		double outOfMarks = outofmarks.doubleValue();
		logger.info("{Marks of subject }"+ markOfSubject);
		logger.info("{outOfMarks }"+outOfMarks);
		double percentage = (markOfSubject/outOfMarks) * 100;
		logger.info("{percentage }"+ percentage);
		
		GradeBean grade = proxy.retrieveGrade(BigDecimal.valueOf(percentage), classno);
		
		logger.info("{grade }"+ grade);
		//HATEOAS
		
		return grade;
//		return marks;
	}
	
	// PUT /marks/students/{studentid}/subjects/{subjectid}/terms/{termtype}
	@PutMapping("/marks/students/{studentid}/subjects/{subjectid}/terms/{termtype}")
	public Resource<Marks> updateTermMarks(@PathVariable String studentid, @PathVariable String subjectid,
			@PathVariable String termtype, @RequestBody Marks reqstMarks){
		
		Marks marks = service.getTermMarks(studentid, subjectid, termtype);
		if(marks == null) {
			throw new MarksNotFoundException("No Marks found for the studentid : " + studentid +  " and subjectid : "+ subjectid + 
					" and TermType : "+ termtype);
		}
		reqstMarks.setMarksId(marks.getMarksId());
		service.updateMarks(reqstMarks);
		reqstMarks.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		logger.info("{Marks value updated }", reqstMarks);
		
		//"all-marks", SERVER_PATH + "/marks/students/{studentid}/subjects/{subjectid}/terms"
		//retrieveAllSubjects
		Resource<Marks> resource = new Resource<Marks>(reqstMarks);
		
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retreiveAllTermMarks(studentid, subjectid));
		
		resource.add(linkTo.withRel("all-marks"));
		
		//HATEOAS
		
		return resource;		
//		return marks;
	}
	
	// DELETE /marks/students/{studentid}/subjects/{subjectid}/terms/{termtype}
	@DeleteMapping("/marks/students/{studentid}/subjects/{subjectid}/terms/{termtype}")
	public Resource<Marks> deleteTermMarks(@PathVariable String studentid, @PathVariable String subjectid,
			@PathVariable String termtype){
		
		Marks marks = service.getTermMarks(studentid, subjectid, termtype);
		if(marks == null) {
			throw new MarksNotFoundException("No Marks found for the studentid : " + studentid +  " and subjectid : "+ subjectid + 
					" and TermType : "+ termtype);
		}		
		logger.info("{Marks value found }", marks);
		service.deleteMarks(marks);
		logger.info("{Marks value deleted }", marks);
		//"all-marks", SERVER_PATH + "/marks/students/{studentid}/subjects/{subjectid}/terms"
		//retrieveAllSubjects
		Resource<Marks> resource = new Resource<Marks>(marks);
		
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retreiveAllTermMarks(studentid, subjectid));
		
		resource.add(linkTo.withRel("all-marks"));
		
		//HATEOAS
		
		return resource;		
//		return marks;
	}
	

}
