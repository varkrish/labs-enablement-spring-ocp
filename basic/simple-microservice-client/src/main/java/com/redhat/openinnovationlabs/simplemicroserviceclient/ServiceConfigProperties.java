package com.redhat.openinnovationlabs.simplemicroserviceclient;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "service")
public class ServiceConfigProperties {
    private String serviceBaseUri = "http://localhost:8100";

    public void setServiceUri(String value) {
        serviceBaseUri = value;
    }

    public String getServiceUri() {
        return serviceBaseUri;
    }
}

