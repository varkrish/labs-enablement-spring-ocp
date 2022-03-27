package com.redhat.openinnovationlabs.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class MicroServiceSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceSampleApplication.class, args);
	}

}
