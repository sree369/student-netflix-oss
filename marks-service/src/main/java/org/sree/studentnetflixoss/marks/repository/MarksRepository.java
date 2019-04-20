package org.sree.studentnetflixoss.marks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sree.studentnetflixoss.marks.model.Marks;

public interface MarksRepository extends JpaRepository<Marks, Long> {

	Marks findByStudentIdAndSubjectIdAndTermType(long studentId, long subjectId, String termType);
	
	List<Marks> findByStudentIdAndSubjectId(long studentId, long subjectId);

//	Marks findByStudentId(long studentId);
}
