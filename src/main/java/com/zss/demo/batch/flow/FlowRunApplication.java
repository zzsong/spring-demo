package com.zss.demo.batch.flow;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class FlowRunApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowRunApplication.class, args);
	}

}
