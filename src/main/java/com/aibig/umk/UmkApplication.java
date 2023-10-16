package com.aibig.umk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.domain.EntityScan;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
// @ComponentScan(basePackages = "com.aibig.umk")
// @EntityScan("com.aibig.umk.model")
// @EnableJpaRepositories(basePackages = "com.aibig.umk.repository")
public class UmkApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmkApplication.class, args);
	}

}
