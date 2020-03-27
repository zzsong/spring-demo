package com.zss.demo.batch.split;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class SplitRunApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplitRunApplication.class, args);
	}

}
