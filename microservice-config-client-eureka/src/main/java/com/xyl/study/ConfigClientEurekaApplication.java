package com.xyl.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by xyl on 11/2/17.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientEurekaApplication.class, args);
    }
}
