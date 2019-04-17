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
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${subjects.class.5.maxmarks.40}")
	private String class5subjectsMaxMarks40;

	@Value("${subjects.class.5.maxmarks.20}")
	private String class5subjectsMaxMarks20;
	
	@Value("${subjects.class.7.maxmarks.80}")
	private String class7subjectsMaxMarks80;
	
	@Value("${subjects.class.7.maxmarks.40}")
	private String class7subjectsMaxMarks40;
	
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
	
	// get all subjects in DB
	public List<Subject> getAllSubjects(){
		return daoService.getAllSubjects();
	}
	
	// delete subject in DB
	public void deleteSubject(String subjectid){
		daoService.deleteSubject(subjectid);
	}
	
	// save subject in DB
	public Subject saveSubject(Subject subject){
		return daoService.saveSubject(subject);
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
	
	public int retrieveMaxmarksByClassnoSubjectShortname(String classno, String subShortName) {
		List<String> temp = new ArrayList<>();
		int maxmarks = 0;
		List<String> subjmax = filterMaxMarksByClassNo(classno);
		for(String sub : subjmax) {
			String[] subject = sub.split(",");
			temp.addAll(Arrays.asList(subject));
		}
		if(classno.equals("5")) {
			maxmarks = filterMaxMarksClass5BySubShortName(subShortName);
		}else if(classno.equals("7")) {
			maxmarks = filterMaxMarksClass7BySubShortName(subShortName);
		}
		return maxmarks;
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
	
	
	public List<String> filterMaxMarksByClassNo(String classno){
		List<String> sub = new ArrayList<>();
		
		switch(classno) {
			case "5":
				sub.add(class5subjectsMaxMarks40);
				sub.add(class5subjectsMaxMarks20);
				break;
				
			case "7":
				sub.add(class7subjectsMaxMarks80);
				sub.add(class7subjectsMaxMarks40);
				break;
				
			default:
				sub = null;
				break;
		}
		
		return sub;
	}
	
	public int filterMaxMarksClass5BySubShortName(String subShortName){
		int marks = 0;
		
		switch(subShortName) {
			case "ENG":
				marks = 40;
				break;
			case "MAT":
				marks = 40;
				break;
			case "HIN":
				marks = 40;
				break;
			case "MAR":
				marks = 40;
				break;
			case "EVS":
				marks = 40;
				break;				
			case "COM":
				marks = 20;
				break;
			default:
				marks = 0;
				break;
		}
		
		return marks;
	}
	
	public int filterMaxMarksClass7BySubShortName(String subShortName){
		int marks = 0;
		
		switch(subShortName) {
			case "ENG":
				marks = 80;
				break;
			case "MAT":
				marks = 80;
				break;
			case "HIN":
				marks = 80;
				break;
			case "GER":
				marks = 80;
				break;
			case "SCI":
				marks = 80;
				break;				
			case "SST":
				marks = 80;
				break;
			case "ICT":
				marks = 40;
				break;
			default:
				marks = 0;
				break;
		}
		
		return marks;
	}
}
