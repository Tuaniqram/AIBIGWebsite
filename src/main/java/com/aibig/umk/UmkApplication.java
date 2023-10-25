package com.aibig.umk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
// @EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class UmkApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmkApplication.class, args);
	}

}
