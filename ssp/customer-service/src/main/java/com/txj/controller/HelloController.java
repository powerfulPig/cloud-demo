package com.txj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @LoadBalanced
    private final RestTemplate restTemplate;

    @Autowired
    public HelloController(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @GetMapping("/order")
    public String getOrder(){
        return restTemplate.getForObject("http://localhost:8000/order/hello/hello",String.class);
    }
}
