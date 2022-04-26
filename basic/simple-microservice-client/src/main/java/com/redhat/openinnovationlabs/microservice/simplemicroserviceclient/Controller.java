package com.redhat.openinnovationlabs.microservice.simplemicroserviceclient;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;

@RestController
public class Controller {
    Counter visitCounter;

    @Value("${baseuri}")
	String baseUri;

    public Controller(MeterRegistry registry){
        visitCounter = Counter.builder("visit_counter")
            .description("Number of visits to the site")
            .register(registry);
    }

    @GetMapping("/servicename")
	public String get()
	{
		RestTemplate resultTmpl = new RestTemplate();
		String result = resultTmpl.getForObject(baseUri +"/appname", String.class);

		return result;
	}
}
