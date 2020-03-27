package com.zss.demo.batch.one;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class OneRunApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneRunApplication.class, args);
	}

}
