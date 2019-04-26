package org.sree.studentnetflixoss.subject.model;

public class GradeBean {


	private long gradationId;
	
	private String grade;

	private int port;

	public GradeBean(long gradationId, String grade, int port) {
		super();
		this.gradationId = gradationId;
		this.grade = grade;
		this.port = port;
	}

	public GradeBean() {
		super();
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
	
	
}
