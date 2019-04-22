package org.sree.studentnetflixoss.gradation.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sree.studentnetflixoss.gradation.model.Standard;
import org.sree.studentnetflixoss.gradation.repository.StandardRepository;

@Component
public class StandardDAOService {

	@Autowired
	private StandardRepository repo;
	
	
	public List<Standard> getAllStandards() {
		return repo.findAll();
	}
	
	
	public Standard saveStandard(Standard standard) {
		return repo.save(standard);
	}
	
	
	public Standard getStandardById(Long standardid) {
		return repo.findById(standardid).get();
	}
	
	public void deleteById(Long standardid) {
		repo.deleteById(standardid);
	}
	
	public Standard findByStandard(int standard) {
		return repo.findByStandard(standard);
	}
}