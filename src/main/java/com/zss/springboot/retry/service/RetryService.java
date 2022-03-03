package com.zss.springboot.retry.service;

import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class RetryService {

    @Retryable(value = IllegalAccessException.class)
    public void example01() throws IllegalAccessException {
        System.out.println("example do something......."+ LocalDateTime.now().toString());
        throw new IllegalAccessException("illegal exception");
    }
}
