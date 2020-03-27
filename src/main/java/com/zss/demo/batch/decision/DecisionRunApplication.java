package com.zss.demo.batch.decision;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class DecisionRunApplication {

	public static void main(String[] args) {
		SpringApplication.run(DecisionRunApplication.class, args);
	}

}
