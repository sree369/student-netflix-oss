package org.sree.studentnetflixoss.marks.dao;

import java.util.List;

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
	
	public Marks findByStudentidAndSubjectIdAndTermType(long studentId, long subjectId, String termType) {
		return repo.findByStudentIdAndSubjectIdAndTermType(studentId, subjectId, termType);
	}
	
	public List<Marks> findByStudentIdAndSubjectId(long studentId, long subjectId) {
		return repo.findByStudentIdAndSubjectId(studentId, subjectId);

	}
	
	public void saveMarksByTermType(long studentId, long subjectId, Marks marks) {
		repo.save(marks);

	}
	
	public void updateMarks(Marks marks) {
		repo.save(marks);

	}

	public void deleteMarks(Marks marks) {
		repo.delete(marks);
	}
	
}
