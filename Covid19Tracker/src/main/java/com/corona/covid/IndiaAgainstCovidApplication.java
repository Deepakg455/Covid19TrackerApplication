package com.corona.covid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IndiaAgainstCovidApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndiaAgainstCovidApplication.class, args);
	}

}
