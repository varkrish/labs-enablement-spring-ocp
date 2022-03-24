package com.redhat.openinnovationlabs.simplemicroserviceclient;

import com.redhat.openinnovationlabs.simplemicroserviceclient.ServiceConfigProperties;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestService {

    @Autowired
    private ClientConfigProperties clientConfig;

    @Autowired
    private ServiceConfigProperties serviceConfig;

    private final AtomicLong counter = new AtomicLong();
	
	@GetMapping("/")
	public String get() {
        RestTemplate restClient = new RestTemplate();
        ResponseEntity<String> response = restClient.getForEntity(serviceConfig.getServiceUri() + "/rest", String.class);

		return " " + counter.incrementAndGet() + ". String from properties file (client.message):" + clientConfig.getMessage() + " --> returned from service: " + response.getBody();
	}
}
