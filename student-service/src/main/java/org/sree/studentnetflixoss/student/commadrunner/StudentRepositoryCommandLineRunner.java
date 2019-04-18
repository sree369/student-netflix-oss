package org.sree.studentnetflixoss.student.commadrunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.sree.studentnetflixoss.student.model.Student;
import org.sree.studentnetflixoss.student.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class StudentRepositoryCommandLineRunner implements CommandLineRunner {

	private static final Logger log = 
			LoggerFactory.getLogger(StudentRepositoryCommandLineRunner.class);
	
	@Autowired
	private StudentRepository repo;
	
	@Override
	public void run(String... args) throws Exception {
//		Student studentA = new Student(1L, "Arpita", "P", "Female", "CIS", 8);
//		
//		repo.save(studentA);
//		
//		log.info("New Student is " + studentA.getFirstName() + " created ");
//		log.info("New Student is created " + studentA);
//		
//		Student studentH = new Student(1L, "Hasit", "P", "Male", "CIS", 6);
//		
//		repo.save(studentH);
//		
//		log.info("New Student is " + studentH.getFirstName() + " created ");
//		log.info("New Student is created " + studentH);
	}

}
