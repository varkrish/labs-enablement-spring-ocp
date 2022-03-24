package com.redhat.openinnovationlabs.microservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestService {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	@Value("${spring.application.name:myapp}")
	private String applicationName;
	@GetMapping("/rest")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return " " + counter.incrementAndGet() + ". " +   String.format(template, name);
	}
	@GetMapping("/appname")
	public String getName() {
		return applicationName;
	}
	
}
