package com.kush.pos.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @Value("${server.port}")
    int port;

    @Value("${spring.application.name}")
    String applicationName;

    @RequestMapping("/process")
    public String test(){
        return applicationName+" started and running on port "+port;
    }


}
