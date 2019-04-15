package org.sree.studentnetflixoss.marks.commandlinerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.sree.studentnetflixoss.marks.model.Marks;
import org.sree.studentnetflixoss.marks.repository.MarksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class MarksRepositoryCommandLineRunner implements CommandLineRunner {

	private static final Logger log = 
			LoggerFactory.getLogger(MarksRepositoryCommandLineRunner.class);
	
	@Autowired
	private MarksRepository marksRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		
// Using data.sql to push values
//		Marks marks7Eng = new Marks(1, 1, "TERM1", 70 );
//		marksRepo.save(marks7Eng);
//		log.info("New Marks is created :------------> " + marks7Eng);
//		
//		Marks marks7Maths = new Marks(1, 2, "TERM1", 44 );
//		marksRepo.save(marks7Maths);
//		log.info("New Marks is created :------------> " + marks7Maths);
//		
//		Marks marks7Hin = new Marks(1, 3, "TERM1", 50 );
//		marksRepo.save(marks7Hin);
//		log.info("New Marks is created :------------> " + marks7Hin);
//			
//		Marks marks7Ger = new Marks(1, 5, "TERM1", 45 );
//		marksRepo.save(marks7Ger);
//		log.info("New Marks is created :------------> " + marks7Ger);
//			
//		Marks marks7Sc = new Marks(1, 6, "TERM1", 55 );
//		marksRepo.save(marks7Sc);
//		log.info("New Marks is created :------------> " + marks7Sc);
//		
//		Marks marks7Sst = new Marks(1, 7, "TERM1", 60 );
//		marksRepo.save(marks7Sst);
//		log.info("New Marks is created :------------> " + marks7Sst);
		
	}

}
