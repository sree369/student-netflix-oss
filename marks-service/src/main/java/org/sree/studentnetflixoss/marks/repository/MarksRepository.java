package org.sree.studentnetflixoss.marks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sree.studentnetflixoss.marks.model.Marks;

public interface MarksRepository extends JpaRepository<Marks, Long> {

	//Marks findByStudentIdSubjectIdTermType(long studentId, long subjectId, String termType);

//	Marks findByStudentId(long studentId);
}
