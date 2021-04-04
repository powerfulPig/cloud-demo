package com.txj.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TemplateConfig {

    @Bean
    @Qualifier("restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
