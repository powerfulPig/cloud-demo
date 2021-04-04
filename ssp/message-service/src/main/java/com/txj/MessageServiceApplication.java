package com.txj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description: some desc
 * @author: txj
 * @email: catchtom@qq.com
 * @date: 2021/4/3 15:42
 */

@SpringBootApplication
@EnableDiscoveryClient
public class MessageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessageServiceApplication.class,args);
    }
}
