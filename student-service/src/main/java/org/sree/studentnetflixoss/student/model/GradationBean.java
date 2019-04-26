package org.sree.studentnetflixoss.student.model;

public class GradationBean {
	
	private long gradationId;
	
	private String grade;
	
	private int port;

	public GradationBean() {
		super();
	}



	public GradationBean(long gradationId, String grade) {
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



	public int getPort() {
		return port;
	}



	public void setPort(int port) {
		this.port = port;
	}



	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	

}
