package org.sree.studentnetflixoss.subject.commandlinerunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.sree.studentnetflixoss.subject.model.Subject;
import org.sree.studentnetflixoss.subject.repository.SubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class SubjectRepositoryCommandLineRunner implements CommandLineRunner {

	private static final Logger log = 
			LoggerFactory.getLogger(SubjectRepositoryCommandLineRunner.class);
	
	
	@Autowired
	private SubjectRepository repo;
	
	@Override
	public void run(String... args) throws Exception {
		// Push using data.sql script
//		Subject subEng = new Subject("ENG", "English");
//		repo.save(subEng);
//		log.info("New Subject is created : " + subEng);
//		
//		Subject subMaths = new Subject("MAT", "Mathematics");
//		repo.save(subMaths);
//		log.info("New Subject is created : " + subMaths);
//		
//		Subject subHindi = new Subject("HIN","Hindi");
//		repo.save(subHindi);
//		log.info("New Subject is created : " + subHindi);
//		
//		Subject subMar = new Subject("MAR", "Marathi");
//		repo.save(subMar);
//		log.info("New Subject is created : " + subMar);
//
//		Subject subG = new Subject("GER", "German");
//		repo.save(subG);
//		log.info("New Subject is created : " + subG);
//		
//		Subject subSc = new Subject("SCI", "Science");
//		repo.save(subSc);
//		log.info("New Subject is created : " + subSc);
//		
//		Subject subSst = new Subject("SST", "Social Science");
//		repo.save(subSst);
//		log.info("New Subject is created : " + subSst);
//		
//		Subject subIct = new Subject("ICT","ICT");
//		repo.save(subIct);
//		log.info("New Subject is created : " + subIct);
//		
//		Subject subEvs = new Subject("EVS", "EVS");
//		repo.save(subEvs);
//		log.info("New Subject is created : " + subEvs);
//		
//		Subject subComp = new Subject("COM", "Computer");
//		repo.save(subComp);
//		log.info("New Subject is created : " + subComp);
	}

}
