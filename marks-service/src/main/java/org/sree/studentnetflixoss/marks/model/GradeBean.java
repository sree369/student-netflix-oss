package org.sree.studentnetflixoss.marks.model;

import javax.validation.constraints.Size;

public class GradeBean {

	private long gradationId;
	
	private String grade;

	private int port;
	
	public GradeBean() {
		super();
	}



	public GradeBean(long gradationId,String grade) {
		super();
		this.gradationId = gradationId;
		this.grade = grade;
	}



	public long getGradationId() {
		return gradationId;
	}



	public void setGradationId(long gradationId) {
		this.gradationId = gradationId;
	}

	public String getGrade() {
		return grade;
	}



	public void setGrade(String grade) {
		this.grade = grade;
	}



	public int getPort() {
		return port;
	}



	public void setPort(int port) {
		this.port = port;
	}



	@Override
	public String toString() {
		return "GradeBean [gradationId=" + gradationId + ", grade=" + grade + "]";
	}
	
	
}
