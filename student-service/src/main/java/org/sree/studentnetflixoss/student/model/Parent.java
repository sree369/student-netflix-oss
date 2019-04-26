package org.sree.studentnetflixoss.student.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the parent.")
@Entity
@Table(name="Parent")
public class Parent {

	@Id
	//GenerationType.IDENTITY use for create primary key
	//GenerationType.AUTO use for create table without primary key
	//for this reason hibernate create a table hibernate_sequence that store 
	//last value (auto incremented value)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="parent_id")
	private long parentId;
	
	@Size(min=2, message="First Name should have atleast 2 characters")
	@ApiModelProperty(notes="First Name should have atleast 2 characters")
	@Column(name="first_name")
	private String firstName;
	
	@Size(min=1, message="Last Name should have atleast 1 characters")
	@ApiModelProperty(notes="Last Name should have atleast 1 characters")
	@Column(name="last_name")
	private String lastName;
	
	@Email
	@ApiModelProperty(notes="Should be a valid emailid of the parent")
	@Column(name="email_id")
	private String emailId;
	
	@OneToMany(mappedBy="parent")
	private List<Student> students;
	
	
	
	

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
