package com.zss.annotationnotes.service;

import org.springframework.stereotype.Component;

@Component
public class WaterMelonServiceIml implements FruitService{
    @Override
    public String fruitName() {
        return "water melon";
    }
}
