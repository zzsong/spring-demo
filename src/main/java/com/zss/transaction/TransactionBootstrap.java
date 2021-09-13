package com.zss.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:transaction/applicationContext-transaction.xml")
public class TransactionBootstrap {


    public static void main(String[] args) {
        SpringApplication.run(TransactionBootstrap.class, args);
    }
}
