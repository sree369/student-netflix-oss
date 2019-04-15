package org.sree.studentnetflixoss.student.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

//import org.sree.studentnetflixoss.subject.model.Subject;

import com.fasterxml.jackson.annotation.JsonInclude;
@Entity
@Table(name="Student")
public class Student {
	
	@Id
	@GeneratedValue
	private long studentId;
	
	private long parentId;


	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String gender;
	@Column
	private String school;
	@Column
	private int studentClass;
	
//	@JsonInclude()
//	@Transient
//	private List<Subject> subjects = new ArrayList();
//	@JsonInclude()
//	@Transient
//	private List<Marks> marks = new ArrayList();
	
	
	public Student() {
		super();
	}
	public Student(long parentId, String firstName, String lastName, String gender, String school, int studentClass) {
		super();
		this.parentId = parentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.school = school;
		this.studentClass = studentClass;
	}

	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public int getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(int studentClass) {
		this.studentClass = studentClass;
	}
	
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	
//	public List<Subject> getSubjects() {
//		return subjects;
//	}
//	public void setSubjects(List<Subject> subjects) {
//		this.subjects = subjects;
//	}
//	public List<Marks> getMarks() {
//		return marks;
//	}
//	public void setMarks(List<Marks> marks) {
//		this.marks = marks;
//	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", parentId=" + parentId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", school=" + school + ", studentClass=" + studentClass + "]";
	}
	

	
	
	

}
