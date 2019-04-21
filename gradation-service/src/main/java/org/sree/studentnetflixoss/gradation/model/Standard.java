package org.sree.studentnetflixoss.gradation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Standard {
	
	@Id
	@GeneratedValue
	private long standardId;
	
	private String standardDesc;
	
	private int standard;

	public Standard() {
		super();
	}

	public Standard(long standardId, String standardDesc, int standard) {
		super();
		this.standardId = standardId;
		this.standardDesc = standardDesc;
		this.standard = standard;
	}

	public long getStandardId() {
		return standardId;
	}

	public void setStandardId(long standardId) {
		this.standardId = standardId;
	}

	public String getStandardDesc() {
		return standardDesc;
	}

	public void setStandardDesc(String standardDesc) {
		this.standardDesc = standardDesc;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	@Override
	public String toString() {
		return "Standard [standardId=" + standardId + ", standardDesc=" + standardDesc + ", standard=" + standard + "]";
	}
	
	
	

}
