package com.redhat.openinnovationlabs.sample.daemonset.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.HashMap;

@RestController
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public HashMap index() throws Exception {
        logger.debug("request served");
        return new HashMap<>() {{
            put("hostname", InetAddress.getLocalHost().getHostName());
        }};
    }
}
