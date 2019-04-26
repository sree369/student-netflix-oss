package org.sree.studentnetflixoss.marks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sree.studentnetflixoss.marks.dao.MarksDAOService;
import org.sree.studentnetflixoss.marks.model.Marks;
import org.sree.studentnetflixoss.marks.repository.MarksRepository;

@Component
public class MarksService {
	@Autowired
	private MarksDAOService dao;
	
	public Marks getTermMarks(String studentid, String subjectid, String termtype) {
		return dao.findByStudentidAndSubjectIdAndTermType(Long.parseLong(studentid), Long.parseLong(subjectid), termtype);
	}
	
	public Marks getTermMarks(String studentid, Integer classno, String subjectid, String termtype) {
		return dao.findByStudentidAndSubjectIdAndTermType(Long.parseLong(studentid), classno, Long.parseLong(subjectid), termtype);
	}
	
	public List<Marks> getAllTermMarks(Long studentid, Long subjectid) {
		return dao.findByStudentIdAndSubjectId(studentid, subjectid);
	}
	
	public void saveMarksByTermType(Long studentid, Long subjectid, Marks marks) {
		dao.saveMarksByTermType(studentid, subjectid, marks);
	}
	public void updateMarks(Marks updateMarks) {
		dao.updateMarks(updateMarks);
	}	
	
	public void deleteMarks(Marks marks) {
		dao.deleteMarks(marks);
	}
	
}
