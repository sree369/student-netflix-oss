package org.sree.studentnetflixoss.subject.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Subject {
	@Id
	@GeneratedValue
	private long subjectid;
	
	private String subShortName;
	
	private String subjectName;
	
	public Subject() {
		super();
	}

	public Subject(String subShortName, String subjectName) {
		super();
		this.subShortName = subShortName;
		this.subjectName = subjectName;
	}

	public long getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(long subjectid) {
		this.subjectid = subjectid;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubShortName() {
		return subShortName;
	}

	public void setSubShortName(String subShortName) {
		this.subShortName = subShortName;
	}

	@Override
	public String toString() {
		return "Subject [subjectid=" + subjectid + ", subShortName=" + subShortName + ", subjectName=" + subjectName
				+ "]";
	}


	

}
