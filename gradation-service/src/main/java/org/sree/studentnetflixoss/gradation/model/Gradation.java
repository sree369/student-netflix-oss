package org.sree.studentnetflixoss.gradation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Gradation {
	
	@Id
	@GeneratedValue
	private long gradationId;
	
	private long maxMarksGrade;
	
	private long minMarksGrade;
	
	private String standard;
	
	private String grade;
	
	

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
