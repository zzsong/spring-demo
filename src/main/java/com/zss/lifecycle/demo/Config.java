package com.zss.lifecycle.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean(initMethod = "initMethod")
    public Lifecycle create(){
        return new Lifecycle();
    }
}
