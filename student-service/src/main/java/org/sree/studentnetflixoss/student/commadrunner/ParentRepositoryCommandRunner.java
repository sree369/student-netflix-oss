package org.sree.studentnetflixoss.student.commadrunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.sree.studentnetflixoss.student.model.Parent;
import org.sree.studentnetflixoss.student.repository.ParentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ParentRepositoryCommandRunner implements CommandLineRunner{

	private static final Logger log = 
			LoggerFactory.getLogger(ParentRepositoryCommandRunner.class);
	
	@Autowired
	private ParentRepository parentRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Parent parent = new Parent("Sree", "P", "abc@xyz.com");
		parentRepository.save(parent);
		log.info("New Parent is created : " + parent);
		
	}
}
