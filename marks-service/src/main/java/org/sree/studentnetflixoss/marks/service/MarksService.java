package org.sree.studentnetflixoss.marks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sree.studentnetflixoss.marks.dao.MarksDAOService;
import org.sree.studentnetflixoss.marks.model.Marks;
import org.sree.studentnetflixoss.marks.repository.MarksRepository;

@Component
public class MarksService {
	@Autowired
	private MarksDAOService dao;
	
	public Marks getTermMarks(String studentid, String subjectid, String termtype) {
		
		
		return dao.getTermMarks(Long.parseLong(studentid), Long.parseLong(subjectid), termtype);
	}

}
