package org.sree.studentnetflixoss.subject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.sree.studentnetflixoss.subject.exception.SubjectNotFoundException;
import org.sree.studentnetflixoss.subject.model.PercentageBean;
import org.sree.studentnetflixoss.subject.model.Subject;
import org.sree.studentnetflixoss.subject.proxy.PercentageProxy;
import org.sree.studentnetflixoss.subject.service.SubjectService;


@RestController
public class SubjectController {
	
	private static final Logger log = LoggerFactory.getLogger(SubjectController.class);
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private PercentageProxy proxy;
	
	@Value("${outofmarks.40.subjects.class.5}")
	private String subjectsOfOutOfMarks;
	
	//  /subject-service/classno/{classno}/subjects
	
	@GetMapping("/subject-service/classno/{classno}/subjects")
	public List<Subject> retreiveSubjects(@PathVariable String classno) {
		List<Subject> subjs = subjectService.getAllSubjectsForClass(classno);
		if(subjs == null || subjs.isEmpty()) {
			
		}
		return subjs;
	}

	
	// /subject-service/subjects/{subjectid}
//	@GetMapping("/subject-service/subjects/{subjectid}")
//	public Subject reteiveSubjectBySubjectId(@PathVariable String classno, @PathVariable String subjectid) {
//		return subjectService.retrieveSubjectBySubjectId(subjectid);
//		//return null;
//	}
	
	// /subject-service/subjects/{subshortname}
	@GetMapping("/subject-service/subjects/{subshortname}")
	public Subject reteiveSubjectShortName(@PathVariable String subshortname) {
		Subject subject = subjectService.retrieveSubjectBySubjectShortName(subshortname);
		if(subject == null) {
			throw new SubjectNotFoundException("subShortName : "+ subshortname);
		}
		return subject;
	}
	
	// /subject-service/subjects/{subshortname}
	@GetMapping("/subject-service/subjectsbyclassno-in-prop/{classno}")
	public List reteiveSubjectsShortnameFromPropertyByClassno(@PathVariable String classno) {
		List subjectsList = subjectService.reteiveSubjectsShortnameFromPropertyByClassno(classno);
		if(subjectsList == null || subjectsList.isEmpty()) {
			throw new SubjectNotFoundException("classno : "+ classno);
		}
		return subjectsList;
	}
	
	// /subject-service/students/{studentid}/subjects/{subjectid}/terms/{termtype}
	@GetMapping("/subject-service/students/{studentid}/subjects/{subjectid}/terms/{termtype}")
	public PercentageBean reteivePercentage(@PathVariable String studentid, @PathVariable String subjectid,
			@PathVariable String termtype) {

		// Feign - Problem 1
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("studentid", studentid);
		uriVariables.put("subjectid", subjectid);
		uriVariables.put("termtype", termtype);

		log.info("HashMap set :--> ");
		ResponseEntity<PercentageBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8200/marks/students/{studentid}/subjects/{subjectid}/terms/{termtype}", PercentageBean.class,
				uriVariables);
		log.info("response entity returned :--> ");
		PercentageBean response = responseEntity.getBody();
		// hard coded value
		double outOf = 80;
		double mark = response.getObtainedMarks();
		double perc = mark/outOf;
		log.info("perc : " + perc + " mark : "+mark);
		
//		Subject sub = subjectService.retrieveSubjectBySubjectId(subjectid);
//		String[] subjects = subjectsOfOutOfMarks.split(",");
//		for(String subj: subjects) {
//			if(subj != null && subj.trim().equals(sub.getSubShortName())) {
//				
//			}
//		}
		return new PercentageBean(response.getMarksId(), Long.parseLong(studentid), Long.parseLong(subjectid), termtype, 
				response.getObtainedMarks(), (int)outOf,perc, response.getPort());
//		return null;
	}
	
	
	// /subject-service/students/{studentid}/subjects/{subjectid}/terms/{termtype}
	@GetMapping("/subject-service-feign/students/{studentid}/subjects/{subjectid}/terms/{termtype}")
	public PercentageBean reteivePercentageFeign(@PathVariable String studentid, @PathVariable String subjectid,
			@PathVariable String termtype) {

//		// Feign - Problem 1
//		Map<String, String> uriVariables = new HashMap<>();
//		uriVariables.put("studentid", studentid);
//		uriVariables.put("subjectid", subjectid);
//		uriVariables.put("termtype", termtype);

		log.info("Proxy set :--> ");
		
		PercentageBean response = proxy.retrieveMarksValue(studentid, subjectid, termtype);
//		ResponseEntity<PercentageBean> responseEntity = new RestTemplate().getForEntity(
//				"http://localhost:8200/marks-service/students/{studentid}/subjects/{subjectid}/terms/{termtype}", PercentageBean.class,
//				uriVariables);
//		log.info("response entity returned :--> ");
//		PercentageBean response = responseEntity.getBody();
		// hard coded value
		double outOf = 80;
		double mark = response.getObtainedMarks();
		double perc = mark/outOf;
		log.info("perc : " + perc + " mark : "+mark);
		
//		Subject sub = subjectService.retrieveSubjectBySubjectId(subjectid);
//		String[] subjects = subjectsOfOutOfMarks.split(",");
//		for(String subj: subjects) {
//			if(subj != null && subj.trim().equals(sub.getSubShortName())) {
//				
//			}
//		}
		return new PercentageBean(response.getMarksId(), Long.parseLong(studentid), Long.parseLong(subjectid), termtype, 
				response.getObtainedMarks(), (int)outOf,perc, response.getPort());
//		return null;
	}
}
