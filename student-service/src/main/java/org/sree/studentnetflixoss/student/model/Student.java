package org.sree.studentnetflixoss.student.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import org.sree.studentnetflixoss.subject.model.Subject;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@Entity
@Table(name="Student")
@ApiModel(description="All details about the student.")
public class Student {
	
	@Id
	@GeneratedValue
	private long studentId;
	
	@Size(min=2, message="First Name of Student should have atleast 2 characters")
	@ApiModelProperty(notes="First Name of Student should have atleast 2 characters")
	private String firstName;
	
	@Size(min=1, message="Last Name of Student should have atleast 2 characters")
	@ApiModelProperty(notes="Last Name of Student should have atleast 2 characters")
	private String lastName;
	
	@ApiModelProperty(notes="Gender of Student")
	private String gender;
	
	@ApiModelProperty(notes="School of Student where they are studying")
	private String school;
	
//	@Size(min=1, message="Class of the Student")
	@ApiModelProperty(notes="Class of the Student")
	private int studentClass;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Parent parent;
//	@JsonInclude()
//	@Transient
//	private List<Subject> subjects = new ArrayList();
//	@JsonInclude()
//	@Transient
//	private List<Marks> marks = new ArrayList();
	
	
	public Student() {
		super();
	}
	public Student(String firstName, String lastName, String gender, String school, int studentClass) {
		super();
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
	
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
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
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", school=" + school + ", studentClass=" + studentClass + "]";
	}
	

	
	
	

}
