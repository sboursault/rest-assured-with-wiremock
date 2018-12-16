package com.sb.controller;

import com.sb.dto.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestApiController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/ping-remote")
    public Resource pingRemote() {
        return restTemplate.getForObject("http://remote/ping", Resource.class);
    }

}
