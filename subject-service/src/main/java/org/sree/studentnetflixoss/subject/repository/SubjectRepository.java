package org.sree.studentnetflixoss.subject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sree.studentnetflixoss.subject.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

	Subject findBySubjectid(long subjectid);
	
	Subject findBySubShortName(String subShortName);
	
}
