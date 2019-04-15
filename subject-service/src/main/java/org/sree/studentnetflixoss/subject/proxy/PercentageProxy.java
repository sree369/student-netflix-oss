package org.sree.studentnetflixoss.subject.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.sree.studentnetflixoss.subject.model.PercentageBean;

//@FeignClient(name="marks-service", url="localhost:8200")
// normal feign client connecting to marks application
//@FeignClient(name="marks-service")
// feign client connecting through zuul api gateway to marks application
@FeignClient(name="zuul-api-gateway-server")
@RibbonClient(name="marks-service")
public interface PercentageProxy {

	// normal feign client connecting to marks application
	//@GetMapping("/marks/students/{studentid}/subjects/{subjectid}/terms/{termtype}")
	// feign client connecting through zuul api gateway to marks application
	@GetMapping("/marks-service/marks/students/{studentid}/subjects/{subjectid}/terms/{termtype}")
	public PercentageBean retrieveMarksValue
		(@PathVariable("studentid") String studentid, @PathVariable("subjectid") String subjectid,
				@PathVariable("termtype") String termtype);
}
