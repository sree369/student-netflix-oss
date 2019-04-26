package org.sree.studentnetflixoss.student.proxy;

import java.math.BigDecimal;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.sree.studentnetflixoss.student.model.GradationBean;


//@FeignClient(name="marks-service", url="localhost:8200")
//normal feign client connecting to marks application
//@FeignClient(name="marks-service")
//feign client connecting through zuul api gateway to marks application
@FeignClient(name="zuul-api-gateway-server")
@RibbonClient(name="subjects-service")
public interface SubjectProxy {
	
	// normal feign client connecting to marks application
	//@GetMapping("/marks/students/{studentid}/subjects/{subjectid}/terms/{termtype}")
	// feign client connecting through zuul api gateway to marks application
	@GetMapping("/subjects-service/subjects-feign/students/{studentid}/classnos/{classno}/subjects/{subjectid}/terms/{termtype}/gradation")
	public GradationBean retrieveGradation(@PathVariable("studentid") String studentid, @PathVariable("classno") BigDecimal classno, 
			@PathVariable("subjectid") String subjectid, @PathVariable("termtype") String termtype);

}
