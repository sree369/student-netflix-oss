package org.sree.studentnetflixoss.marks.proxy;

import java.math.BigDecimal;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.sree.studentnetflixoss.marks.model.GradeBean;

//@FeignClient(name="marks-service", url="localhost:8200")
//normal feign client connecting to marks application
//@FeignClient(name="marks-service")
//feign client connecting through zuul api gateway to marks application
@FeignClient(name="zuul-api-gateway-server")
@RibbonClient(name="gradation-service")
public interface GradationProxy {

	
	// normal feign client connecting to gardation application
	//@GetMapping("/marks/students/{studentid}/subjects/{subjectid}/terms/{termtype}")
	// feign client connecting through zuul api gateway to gardation application
	@GetMapping("/gradation-service/gradation/percentage/{percentage}/classno/{classno}")
	public GradeBean retrieveGrade(@PathVariable("percentage") BigDecimal percentage, @PathVariable("classno") BigDecimal classno);

}
