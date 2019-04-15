package org.sree.studentnetflixoss.subject.model;

public class PercentageBean {

	private long marksId;
	
	private long studentId;
	
	private long subjectId;
	
	private String termType;
	
	private int obtainedMarks;
	
	private int outOf;
	
	private double percentage;
	
	private int port;

	public PercentageBean() {
		super();
	}

	
	public PercentageBean(long marksId, long studentId, long subjectId, String termType, int obtainedMarks, int outOf,
			double percentage, int port) {
		super();
		this.marksId = marksId;
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.termType = termType;
		this.obtainedMarks = obtainedMarks;
		this.outOf = outOf;
		this.percentage = percentage;
		this.port = port;
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

	public int getOutOf() {
		return outOf;
	}

	public void setOutOf(int outOf) {
		this.outOf = outOf;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}


	@Override
	public String toString() {
		return "PercentageBean [marksId=" + marksId + ", studentId=" + studentId + ", subjectId=" + subjectId + ", termType="
				+ termType + ", obtainedMarks=" + obtainedMarks + ", outOf=" + outOf + ", percentage=" + percentage
				+ ", port=" + port + "]";
	}
	
	
	
}
