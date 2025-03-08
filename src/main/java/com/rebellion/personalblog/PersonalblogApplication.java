package com.rebellion.personalblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rebellion")
public class PersonalblogApplication {

	// TODO: Better success/failure/error alerts to be placed.
	public static void main(String[] args) {
		SpringApplication.run(PersonalblogApplication.class, args);
	}
}
