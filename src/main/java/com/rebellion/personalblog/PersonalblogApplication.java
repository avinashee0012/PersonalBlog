package com.rebellion.personalblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rebellion")
public class PersonalblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalblogApplication.class, args);
	}
}
