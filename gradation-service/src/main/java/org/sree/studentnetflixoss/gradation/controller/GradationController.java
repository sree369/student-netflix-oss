package org.sree.studentnetflixoss.gradation.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.sree.studentnetflixoss.gradation.exception.GradationNotFoundException;
import org.sree.studentnetflixoss.gradation.model.Gradation;
import org.sree.studentnetflixoss.gradation.service.GradationService;


@RestController
public class GradationController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private GradationService service;
	
	
	// get all gradations
	//GET /gradation-service/gradations
	@GetMapping("/gradation-service/gradations")
	public List<Gradation> retrieveAllGradations() {
		return service.getAllGradations();
	}
	
	
	//Create a gradation	
	//POST /gradation-service/gradations
	@PostMapping("/gradation-service/gradations")
	public ResponseEntity<Object> saveGradation(@Valid @RequestBody Gradation grade) {
		Gradation savedGrade = service.saveGradation(grade);
		if(savedGrade == null) {
			throw new GradationNotFoundException("Gradation not created : ");
		}
		
		// CREATED
		// /gradation-service/gradations     
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{gradationid}")
			.buildAndExpand(savedGrade.getGradationId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	
	//get gradation by gradationid					  			
	//GET /gradation-service/gradations/{gradationid}
	@GetMapping("/gradation-service/gradations/{gradationid}")
	public Resource<Gradation> retrieveGradeById(@PathVariable BigDecimal gradationid) {
		Gradation grade = service.retrieveGradeById(gradationid.longValue());
		log.info("gradation : " + grade);
		if(grade == null) {
			throw new GradationNotFoundException("gradationid : "+ gradationid);
		}
		
		//"all-gradations", SERVER_PATH + "/gradation-service/gradations"
		//retrieveAllGradations
		Resource<Gradation> resource = new Resource<Gradation>(grade);
		
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllGradations());
		
		resource.add(linkTo.withRel("all-gradations"));
		
		//HATEOAS
		
		return resource;
	}
	
	//Update gradation by gradationid		
	//PUT /gradation-service/gradations/{gradationid}
	@PutMapping("/gradation-service/gradations/{gradationid}")
	public Gradation updateGradeById(@PathVariable BigDecimal gradationid, 
			@Valid @RequestBody Gradation grade) {
		Gradation selectedGrade = service.retrieveGradeById(gradationid.longValue());
		
		if(selectedGrade == null) {
			throw new GradationNotFoundException("gradationid : "+ gradationid);
		}
		grade.setGradationId(selectedGrade.getGradationId());
		service.saveGradation(grade);
	
		return grade;
	}
	
	
	
	// Delete gradation by gradationid					  			
	//DELETE /gradation-service/gradations/{gradationid}
	@DeleteMapping("/gradation-service/gradations/{gradationid}")
	public void deleteGradeById(@PathVariable BigDecimal gradationid) {
		service.deleteGradeById(gradationid.longValue());
	}
	
	//get grades for marks and classno							
	//GET /gradation-service/marks/{marks}/classno/{classno}
	// TODO

}
