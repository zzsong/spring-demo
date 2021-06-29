package com.zss.annotationnotes.service;

import org.springframework.stereotype.Component;

@Component
public class AppleServiceIml implements FruitService{
    @Override
    public String fruitName() {
        return "apple";
    }
}
