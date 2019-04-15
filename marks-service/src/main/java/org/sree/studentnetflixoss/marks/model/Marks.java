package org.sree.studentnetflixoss.marks.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Marks {
	@Id
	@GeneratedValue
	private long marksId;
	
	private long studentId;
	
	private long subjectId;
	
	private String termType;
	
	private int obtainedMarks;
	
	@JsonInclude()
	@Transient
	private int port;
	
//	private int maximumMarks;
//	
////	private double percentage;
//	
//	private String grade;
//	
//	private String result;

	
	
	public Marks() {
		super();
	}

	public Marks(long marksId, long studentId, long subjectId, String termType, int obtainedMarks) {
		super();
		this.marksId = marksId;
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.termType = termType;
		this.obtainedMarks = obtainedMarks;
//		this.maximumMarks = maximumMarks;
//		this.percentage = percentage;
//		this.grade = grade;
//		this.result = result;
	}

	public long getMarksId() {
		return marksId;
	}

	public void setMarksId(long marksId) {
		this.marksId = marksId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getTermType() {
		return termType;
	}

	public void setTermType(String termType) {
		this.termType = termType;
	}

	public int getObtainedMarks() {
		return obtainedMarks;
	}

	public void setObtainedMarks(int obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "Marks [marksId=" + marksId + ", studentId=" + studentId + ", subjectId=" + subjectId + ", termType="
				+ termType + ", obtainedMarks=" + obtainedMarks + ", port=" + port + "]";
	}


	
	

//	public int getMaximumMarks() {
//		return maximumMarks;
//	}
//
//	public void setMaximumMarks(int maximumMarks) {
//		this.maximumMarks = maximumMarks;
//	}
//
//	public double getPercentage() {
//		return percentage;
//	}
//
//	public void setPercentage(double percentage) {
//		this.percentage = percentage;
//	}
//
//	public String getGrade() {
//		return grade;
//	}
//
//	public void setGrade(String grade) {
//		this.grade = grade;
//	}
//
//	public String getResult() {
//		return result;
//	}
//
//	public void setResult(String result) {
//		this.result = result;
//	}
	
	

}

enum TERM
{
	TERM1,
	TERM2;
}