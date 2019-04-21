package org.sree.studentnetflixoss.subject.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.sree.studentnetflixoss.subject.exception.MaxMarksNotFoundException;
import org.sree.studentnetflixoss.subject.exception.SubjectNotFoundException;
import org.sree.studentnetflixoss.subject.model.PercentageBean;
import org.sree.studentnetflixoss.subject.model.Subject;
import org.sree.studentnetflixoss.subject.proxy.PercentageProxy;
import org.sree.studentnetflixoss.subject.service.SubjectService;


@RestController
public class SubjectController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private PercentageProxy proxy;
		
	// retrieve all Subjects from DB
	// /subject-service/subjects
	@GetMapping("/subject-service/subjects")
	public List<Subject> retrieveAllSubjects() {
		return subjectService.getAllSubjects();
	}
	
	//  /subject-service/classnos/{classno}/subjects
	@GetMapping("/subject-service/classnos/{classno}/subjects")
	public List<Subject> retreiveSubjects(@PathVariable String classno) {
		List<Subject> subjs = subjectService.getAllSubjectsForClass(classno);
		if(subjs == null || subjs.isEmpty()) {
			throw new SubjectNotFoundException("classno : "+ classno);
		}
		return subjs;
	}

	
	// /subject-service/subjects/{subjectid}
//	@GetMapping("/subject-service/subjects/{subjectid}")
//	public Subject reteiveSubjectBySubjectId(@PathVariable String classno, @PathVariable String subjectid) {
//		return subjectService.retrieveSubjectBySubjectId(subjectid);
//		//return null;
//	}
	
	// /subject-service/subjects/{subjectid}
	@DeleteMapping("/subject-service/subjects/{subjectid}")
	public void deleteSubjectBySubjectId(@PathVariable String subjectid) {
		subjectService.deleteSubject(subjectid);
	}
	
	// /subject-service/subjects/{subshortname}
	@GetMapping("/subject-service/subjects/{subshortname}")
	public Resource<Subject> reteiveSubjectShortName(@PathVariable String subshortname) {
		Subject subject = subjectService.retrieveSubjectBySubjectShortName(subshortname);
		if(subject == null) {
			throw new SubjectNotFoundException("subShortName : "+ subshortname);
		}
		
		//"all-subjects", SERVER_PATH + "/subject-service/subjects"
		//retrieveAllSubjects
		Resource<Subject> resource = new Resource<Subject>(subject);
		
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllSubjects());
		
		resource.add(linkTo.withRel("all-subjects"));
		
		//HATEOAS
		
		return resource;
		//return subject;
	}
	
	// get subjects in property file as per their classno
	// /subject-service/subjectsbyclassno-in-prop/{classno}
	@GetMapping("/subject-service/subjectsbyclassno-in-prop/{classno}")
	public List reteiveSubjectsShortnameFromPropertyByClassno(@PathVariable String classno) {
		List subjectsList = subjectService.reteiveSubjectsShortnameFromPropertyByClassno(classno);
		if(subjectsList == null || subjectsList.isEmpty()) {
			throw new SubjectNotFoundException("classno : "+ classno);
		}
		return subjectsList;
	}
	
	// /subject-service/classnos/{classno}/subjects/{subshortname}/maxmarks
	@GetMapping("/subject-service/classnos/{classno}/subjects/{subshortname}/maxmarks")
	public int retrieveMaxmarksByClassnoSubjectShortname(@PathVariable String classno, @PathVariable String subshortname) {
		int maxmarks = subjectService.retrieveMaxmarksByClassnoSubjectShortname(classno, subshortname);
		if(maxmarks == 0) {
			throw new MaxMarksNotFoundException("classno : "+ classno + " subshortname : "+ subshortname);
		}
		return maxmarks;
	}
	
	// input - details of subject
	// output - CREATED & Return the created URI
	
	//HATEOAS
	// save Subject to DB
	// /subject-service/subjects
	@PostMapping("/subject-service/subjects")
	public ResponseEntity<Object> saveSubject(@RequestBody Subject subject) {
		 Subject savedSubject = subjectService.saveSubject(subject);
		  
			// CREATED
			// /subjects/{subshortname}     
			
			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{subjectid}")
				.buildAndExpand(savedSubject.getSubjectid()).toUri();
			
			return ResponseEntity.created(location).build();
	}
	
	// /subject-service/students/{studentid}/subjects/{subjectid}/terms
	//TODO
	
	
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
	
	// /subject-service-feign/students/{studentid}/subjects/{subjectid}/terms
	// TODO
	
	// /subject-service-feign/students/{studentid}/subjects/{subjectid}/terms/{termtype}
	@GetMapping("/subject-service-feign/students/{studentid}/subjects/{subjectid}/terms/{termtype}")
	public PercentageBean reteivePercentageFeign(@PathVariable String studentid, @PathVariable String subjectid,
			@PathVariable String termtype) {

		log.info("Proxy set :--> ");
		
		PercentageBean response = proxy.retrieveMarksValue(studentid, subjectid, termtype);
		// hard coded value
		double outOf = 80;
		double mark = response.getObtainedMarks();
		double perc = mark/outOf;
		log.info("perc : " + perc + " mark : "+mark);
		
		return new PercentageBean(response.getMarksId(), Long.parseLong(studentid), Long.parseLong(subjectid), termtype, 
				response.getObtainedMarks(), (int)outOf,perc, response.getPort());
//		return null;
	}
}
