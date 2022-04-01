package com.redhat.openinnovationlabs.sample.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.task.configuration.DefaultTaskConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class HelloWorldTaskConfig extends DefaultTaskConfigurer {

    @Autowired
    private DataSource dataSource;

    public HelloWorldTaskConfig(DataSource dataSource){
        super(dataSource);
    }

    @Bean
    public HelloWorldTaskConfig getTaskConfigurer() {
        return new HelloWorldTaskConfig(dataSource);
    }
}
