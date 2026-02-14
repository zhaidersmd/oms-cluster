package com.foundation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OmsCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmsCustomerServiceApplication.class, args);
	}

}
