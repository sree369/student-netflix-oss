package org.sree.studentnetflixoss.gradation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sree.studentnetflixoss.gradation.dao.GradationDAOService;
import org.sree.studentnetflixoss.gradation.model.Gradation;

@Component
public class GradationService {
	
	@Autowired
	private GradationDAOService dao;
	
	public List<Gradation> getAllGradations(){
		return dao.getAllGradations();
	}
	
	public Gradation saveGradation(Gradation grade) {
		return dao.saveGradation(grade);
	}
	
	public Gradation retrieveGradeById(Long gradationid) {
		return dao.retrieveGradeById(gradationid);
	}
	
	public void deleteGradeById(Long gradationid) {
		dao.deleteGradeById(gradationid);
	}
	
	public Gradation findGradationForPercentage(Long percentage, String stdesc) {
		return dao.findGradationForPercentage(percentage, stdesc);
	}
	
	
	

}
