package org.sree.studentnetflixoss.gradation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sree.studentnetflixoss.gradation.dao.StandardDAOService;
import org.sree.studentnetflixoss.gradation.model.Standard;

@Component
public class StandardService {
	
	@Autowired
	private StandardDAOService dao;
	
	public List<Standard> getAllStandards() {
		return dao.getAllStandards();
	}

	public Standard saveStandard(Standard standard) {
		return dao.saveStandard(standard);
	}
	
	public Standard getStandardById(Long standardid) {
		return dao.getStandardById(standardid);
	}
	
	public void deleteById(Long standardid) {
		dao.deleteById(standardid);
	}
	
	public Standard findByStandard(int standard) {
		return dao.findByStandard(standard);
	}
	
}
