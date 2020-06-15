package com.amdocs.media.assignement.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableFeignClients(basePackages = {"com.*"})
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.amdocs.media.assignement.dao"})
@ComponentScan(basePackages = {"com.*"})
@EntityScan("com.*") 
public class UserAuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationServiceApplication.class, args);
		log.info("hi from root");
		
	}
		

}
