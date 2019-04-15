package org.sree.studentnetflixoss.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sree.studentnetflixoss.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
