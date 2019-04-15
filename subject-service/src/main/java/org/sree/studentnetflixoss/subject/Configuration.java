package org.sree.studentnetflixoss.subject;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("subjects-service")
public class Configuration {
	
	private String subjects_class_5;
	
	private String subjects_class_7;

	public String getSubjects_class_5() {
		return subjects_class_5;
	}

	public void setSubjects_class_5(String subjects_class_5) {
		this.subjects_class_5 = subjects_class_5;
	}

	public String getSubjects_class_7() {
		return subjects_class_7;
	}

	public void setSubjects_class_7(String subjects_class_7) {
		this.subjects_class_7 = subjects_class_7;
	}
	
	

}
