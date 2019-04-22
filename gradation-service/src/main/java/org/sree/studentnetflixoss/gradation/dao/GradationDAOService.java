package org.sree.studentnetflixoss.gradation.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sree.studentnetflixoss.gradation.model.Gradation;
import org.sree.studentnetflixoss.gradation.repository.GradationRepository;

@Component
public class GradationDAOService {
	
	@Autowired
	private GradationRepository repo;
	
	public List<Gradation> getAllGradations(){
		return repo.findAll();
	}

	public Gradation saveGradation(Gradation grade) {
		return repo.save(grade);
	}
	
	public Gradation retrieveGradeById(Long gradationid) {
		return repo.findById(gradationid).get();
	}
	
	public void deleteGradeById(Long gradationid) {
		repo.deleteById(gradationid);
	}
	
	
	public Gradation findGradationForPercentage(Long percentage, String stddesc) {
		Long perc = percentage;
		
		return repo.findOneByMaxMarksGradeGreaterThanEqualAndMinMarksGradeLessThanEqualAndStandard(percentage, perc, stddesc);
//		return repo.findOneByGreaterThanMinMarksGradeAndLessThanMaxMarksGrade(percentage, stddesc);
	}
}
