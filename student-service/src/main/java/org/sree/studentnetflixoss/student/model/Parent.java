package org.sree.studentnetflixoss.student.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="Parent")
public class Parent {

	@Id
	@GeneratedValue
	private long parentId;
	
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String emailId;
	
	@JsonInclude()
	@Transient
	private List<Student> students = new ArrayList();
	
	
	
	

	public Parent() {
		super();
	}

	public Parent(String firstName, String lastName, String emailId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
//		this.students = students;
	}



	@Override
	public String toString() {
		return "Parent [parentId=" + parentId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId="
				+ emailId + "]";
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	
	public long getParentId() {
		return parentId;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	} 
	
	
	
}
