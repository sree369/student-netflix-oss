package org.sree.studentnetflixoss.gradation.commandlinerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.sree.studentnetflixoss.gradation.model.Gradation;
import org.sree.studentnetflixoss.gradation.repository.GradationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class GradationRepositoryCommandLineRunner implements CommandLineRunner {

	private static final Logger log = 
			LoggerFactory.getLogger(GradationRepositoryCommandLineRunner.class);
	
	@Autowired
	private GradationRepository gradRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		
// Using data.sql to push values
//		Gradation gradPri1 = new Gradation(200, 90, 100, "Primary", "A+" );
//		gradRepo.save(gradPri1);
//		log.info("New Gradation is created :------------> " + gradPri1);
//		
//		Gradation gradPri2 = new Gradation(201, 70, 89, "Primary", "A" );
//		gradRepo.save(gradPri2);
//		log.info("New Gradation is created :------------> " + gradPri2);
//		
//		Gradation gradPri3 = new Gradation(202, 50, 69, "Primary", "B+" );
//		gradRepo.save(gradPri3);
//		log.info("New Gradation is created :------------> " + gradPri3);
//			
//		Gradation gradSec1 = new Gradation(203, 91, 100, "Secondary", "A1" );
//		gradRepo.save(gradSec1);
//		log.info("New Gradation is created :------------> " + gradSec1);
//		
//		Gradation gradSec2 = new Gradation(204, 81, 90, "Secondary", "A2" );
//		gradRepo.save(gradSec2);
//		log.info("New Gradation is created :------------> " + gradSec2);
//		
//		Gradation gradSec3 = new Gradation(205, 71, 80, "Secondary", "B1" );
//		gradRepo.save(gradSec3);
//		log.info("New Gradation is created :------------> " + gradSec3);
//		
	}

}
