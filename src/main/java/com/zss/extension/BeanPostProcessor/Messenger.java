package com.zss.extension.BeanPostProcessor;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class Messenger implements InitializingBean {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    private void init(){
        System.out.println("@PostConstruct init");
    }

    private void initMethod(){
        System.out.println("xml配置initMethod");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(">>>>>>>>>>InitializingBean  afterPropertiesSet");
    }
}
