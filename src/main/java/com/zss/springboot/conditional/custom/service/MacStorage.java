package com.zss.springboot.conditional.custom.service;

public class MacStorage implements SystemStorage{
    @Override
    public String loadStorage() {
        return "Mac OS";
    }
}
