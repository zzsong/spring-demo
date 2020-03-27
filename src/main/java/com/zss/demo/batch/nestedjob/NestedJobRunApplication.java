package com.zss.demo.batch.nestedjob;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class NestedJobRunApplication {

	public static void main(String[] args) {
		SpringApplication.run(NestedJobRunApplication.class, args);
	}

}
