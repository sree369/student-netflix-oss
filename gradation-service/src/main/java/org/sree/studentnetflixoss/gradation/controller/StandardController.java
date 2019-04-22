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
import org.sree.studentnetflixoss.gradation.exception.StandardNotFoundException;
import org.sree.studentnetflixoss.gradation.model.Gradation;
import org.sree.studentnetflixoss.gradation.model.Standard;
import org.sree.studentnetflixoss.gradation.service.StandardService;

@RestController
public class StandardController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StandardService service;
	
	// GET /gradation-service/standards
	@GetMapping("/gradation-service/standards")
	public List<Standard> retreiveAllStandards() {
		
		return service.getAllStandards();
		
	}
	
	
	// POST /gradation-service/standards
	@PostMapping("/gradation-service/standards")
	public ResponseEntity<Object> addNewStandard(@Valid @RequestBody Standard standard) {
		
		Standard saved = service.saveStandard(standard);
		if(saved == null)
			throw new StandardNotFoundException("Standard Not created ...");
		
		// CREATED
		// /gradation-service/gradations     
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{standardid}")
			.buildAndExpand(saved.getStandardId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	
	// GET /gradation-service/standards/{standardid}
	@GetMapping("/gradation-service/standards/{standardid}")
	public Resource<Standard> retreiveStandardById(@PathVariable BigDecimal standardid) {
		
		Standard standard = service.getStandardById(standardid.longValue());
		log.info("standard : " + standard);
		if(standard == null) {
			throw new StandardNotFoundException("standardid : "+ standardid);
		}
		
		//"all-standards", SERVER_PATH + "/gradation-service/standards"
		//retreiveAllStandards
		Resource<Standard> resource = new Resource<Standard>(standard);
		
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retreiveAllStandards());
		
		resource.add(linkTo.withRel("all-standards"));
		
		//HATEOAS
		
		return resource;
		
	}
	
	
	// PUT /gradation-service/standards/{standardid}
	@PutMapping("/gradation-service/standards/{standardid}")
	public Standard retreiveStandardById(@PathVariable BigDecimal standardid, @Valid @RequestBody Standard toUpdateStandard) {
		
		Standard standardbyid = service.getStandardById(standardid.longValue());
		log.info("standard : " + standardbyid);
		if(standardbyid == null) {
			throw new StandardNotFoundException("standardid : "+ standardid);
		}
		
		toUpdateStandard.setStandardId(standardbyid.getStandardId());
		service.saveStandard(toUpdateStandard);
	
		return toUpdateStandard;
		
	}
	
	
	//DELETE /gradation-service/standards/{standardid}
	@DeleteMapping("/gradation-service/standards/{standardid}")
	public void deleteStandardById(@PathVariable BigDecimal standardid) {
		
		service.deleteById(standardid.longValue());
		log.info("standard by id deleted : " + standardid);
		
	}

}
