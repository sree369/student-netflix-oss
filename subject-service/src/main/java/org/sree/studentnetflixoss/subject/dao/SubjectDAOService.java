package org.sree.studentnetflixoss.subject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.sree.studentnetflixoss.subject.model.Subject;
import org.sree.studentnetflixoss.subject.repository.SubjectRepository;

@Repository
@Transactional
public class SubjectDAOService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private SubjectRepository repo;	
	
	public List<Subject> getAllSubjects(){
		
		List<Subject> subjects = repo.findAll();
		return subjects;
	}
	
}
