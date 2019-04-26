package org.sree.studentnetflixoss.gradation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Gradation {
	
	@Id
	@GeneratedValue
	private long gradationId;
	
	private long maxMarksGrade;
	
	private long minMarksGrade;
	
	private String standard;
	
	@Size(max=2, message="Grade should be of 2 characters size only")
	
	private String grade;
	
	@JsonInclude()
	@Transient
	private int port;
	

	public int getPort() {
		return port;
	}



	public void setPort(int port) {
		this.port = port;
	}



	public Gradation() {
		super();
	}



	public Gradation(long gradationId, long maxMarksGrade, long minMarksGrade, String standard, String grade) {
		super();
		this.gradationId = gradationId;
		this.maxMarksGrade = maxMarksGrade;
		this.minMarksGrade = minMarksGrade;
		this.standard = standard;
		this.grade = grade;
	}



	public long getGradationId() {
		return gradationId;
	}



	public void setGradationId(long gradationId) {
		this.gradationId = gradationId;
	}



	public long getMaxMarksGrade() {
		return maxMarksGrade;
	}



	public void setMaxMarksGrade(long maxMarksGrade) {
		this.maxMarksGrade = maxMarksGrade;
	}



	public long getMinMarksGrade() {
		return minMarksGrade;
	}



	public void setMinMarksGrade(long minMarksGrade) {
		this.minMarksGrade = minMarksGrade;
	}



	public String getStandard() {
		return standard;
	}



	public void setStandard(String standard) {
		this.standard = standard;
	}



	public String getGrade() {
		return grade;
	}



	public void setGrade(String grade) {
		this.grade = grade;
	}



	@Override
	public String toString() {
		return "Gradation [gradationId=" + gradationId + ", maxMarksGrade=" + maxMarksGrade + ", minMarksGrade="
				+ minMarksGrade + ", standard=" + standard + ", grade=" + grade + "]";
	}
	
	
	
	
	

}
