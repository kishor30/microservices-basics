package com.uxpsystems.assignment.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableEurekaClient 
@SpringBootApplication
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories("com.uxpsystems.assignment.*")
@EntityScan("com.uxpsystems.assignment.*") 
public class SpringBootAssigmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAssigmentApplication.class, args);
		log.info("Hi from root profile");
	}
}


