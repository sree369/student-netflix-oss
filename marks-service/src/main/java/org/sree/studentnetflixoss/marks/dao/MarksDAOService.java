package org.sree.studentnetflixoss.marks.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sree.studentnetflixoss.marks.model.Marks;
import org.sree.studentnetflixoss.marks.repository.MarksRepository;


@Component
public class MarksDAOService {
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private MarksRepository repo;
	
	public Marks getTermMarks(long studentId, long subjectId, String termType) {
		
//		return repo.findByStudentIdSubjectIdTermType(studentId, subjectId, termType);
//		Query qu = entityManager.createQuery("select * from marks where studentid=? and subjectid=? and termType=?");
//		qu.set
	//	Marks marks = (Marks)qu.getSingleResult();
		// hard coding till the DB issue is solved
		return new Marks(1, 1, 1, "TERM1", 70);
	}
}
