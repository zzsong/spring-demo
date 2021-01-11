package com.zss.kernel.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfiguration {


    //不指定，默认使用方法名（getPerson）作为name。
    @Bean(name = "person")
    public Person getPerson(){
        Person person = new Person();
        System.out.println("创建Bean对象....");
        person.setAge(20);
        person.setName("zhangshan");
        return person;
    }
}
