package com.redhat.openinnovationlabs.microservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class RestService {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	@Value("${app.name:myapp}")
	private String applicationName;
    
	Logger logger = LoggerFactory.getLogger(RestService.class);

	@GetMapping("/rest")
	@Timed(value="greeting.timed")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		logger.info("/rest called");
		return " " + counter.incrementAndGet() + ". " +   String.format(template, name);
	}
	@GetMapping("/appname")
	@Timed(value="getName.timed")
	public String getName() {
		logger.info(applicationName);
		return applicationName;
	}
	
}
