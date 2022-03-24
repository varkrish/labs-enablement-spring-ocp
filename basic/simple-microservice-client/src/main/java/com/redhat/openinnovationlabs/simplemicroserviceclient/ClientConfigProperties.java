package com.redhat.openinnovationlabs.simplemicroserviceclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "client")
public class ClientConfigProperties {
    private String message = "Welcome from Spring local";
    
    public void setMessage(String value) {
        message = value;
    }

    public String getMessage() {
        return message;
    }
}
