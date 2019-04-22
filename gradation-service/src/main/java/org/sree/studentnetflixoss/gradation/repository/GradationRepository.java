package org.sree.studentnetflixoss.gradation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.sree.studentnetflixoss.gradation.model.Gradation;

public interface GradationRepository extends JpaRepository<Gradation, Long>{
	
	//@Query(value = "select g from gradation g where (g.maxmarksgrade >= ?1 and g.minmarksgrade <= ?1) and g.standard = ?2", nativeQuery = true)
	Gradation findOneByMaxMarksGradeGreaterThanEqualAndMinMarksGradeLessThanEqualAndStandard(Long percentage, Long perc, String stddesc);

}
