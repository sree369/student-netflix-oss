package org.sree.studentnetflixoss.subject.service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.sree.studentnetflixoss.subject.Configuration;
import org.sree.studentnetflixoss.subject.dao.SubjectDAOService;
import org.sree.studentnetflixoss.subject.model.Subject;
import org.sree.studentnetflixoss.subject.repository.SubjectRepository;

@Component
public class SubjectService {
	
	private static final Logger log = LoggerFactory.getLogger(SubjectService.class);
	
	@Autowired
	private SubjectDAOService daoService;
	@Autowired
	private Environment env;
	@Autowired
	private Configuration configuration;
	@Autowired
	private SubjectRepository repo;
	
	private List subjects_class_5; 
	
	private List subjects_class_7;
	
	public void loadproperties(){
			// Intialize from property
			log.info(":----------------> ");
			String class5 = configuration.getSubjects_class_5();//env.getProperty("subjects_class_5"); //
			if(class5 != null) {
		    	String[] subjectsOf5 = class5.split(",");
		    	subjects_class_5 = new ArrayList();
		    	subjects_class_5.addAll(Arrays.asList(subjectsOf5));
		    	log.info("subjects_class_5 : "+ subjects_class_5.size());
			}else {
				log.info("subjects_class_5 :------------> is null ");
			}
	    	
	    	String class7 = configuration.getSubjects_class_7();//env.getProperty("subjects_class_7");//
	    	if(class7 != null) {
		    	String[] subjectsOf7 = class7.split(",");
		    	subjects_class_7 = new ArrayList();
		    	subjects_class_7.addAll(Arrays.asList(subjectsOf7));
		    	log.info("subjects_class_7 : "+ subjects_class_7.size());
	    	}else {
				log.info("subjects_class_7 :------------> is null ");
			}
			    // now you have name and value	
		}

	// return a Subjects for the particular class
	public List<Subject> getAllSubjectsForClass(String classno) {
		loadproperties();
		List<Subject> dbSub = daoService.getAllSubjects();
		log.info("dbSub : "+ dbSub.size());
		List<Subject> temp = new ArrayList<>();
		
		List propSub = filterSubjectsByClassNo(classno);
		log.info("propSub : "+ propSub.size());
		if(propSub != null && !propSub.isEmpty()) {
			Iterator itr = propSub.iterator();		
			while(itr.hasNext()) {
				String subCode = (String)itr.next();
				log.info("subCode : "+ subCode);
				for (Subject subject:dbSub) {
//					Subject subject = (Subject)dbSub.get(i);
					log.info("dbSub : "+ subject.getSubShortName());
					if(subject != null && subject.getSubShortName().equals(subCode.trim())) {
						temp.add(subject);
						log.info("Foiund dbSub : "+ subject.getSubjectName());
						break;
					}
				}
				
			}
		}
		return temp;
	}
	
	// return Subject by subjectId
	public Subject retrieveSubjectBySubjectId(String subjectid) {
		
		
//		List<Subject> dbSub = daoService.getAllSubjects();
//		Subject selectSubject=null;
//		while(dbSub.iterator().hasNext()) {
//			Subject subject = dbSub.iterator().next();
//			if(subject.getSubjectid() == Long.parseLong(subjectid)) {
//				selectSubject= subject;
//				break;
//			}
//		}
		// direct call to repository 
		return repo.findBySubjectid(Long.parseLong(subjectid));
	}
	
	
	// return Subject by subjectShortName
	public Subject retrieveSubjectBySubjectShortName(String subjectShortName) {
		
		// direct call to repository 
		return repo.findBySubShortName(subjectShortName);
	}
	
	// return Subjects defined in property file
	public List reteiveSubjectsShortnameFromPropertyByClassno(String classno) {
		loadproperties();
		return filterSubjectsByClassNo(classno);		
	}
	
	public List filterSubjectsByClassNo(String classno){
		List sub = new ArrayList();
		
		switch(classno) {
			case "5":
				sub = subjects_class_5;
				break;
				
			case "7":
				sub = subjects_class_7;
				break;
				
			default:
				sub = null;
				break;
		}
		
		return sub;
	}
}
