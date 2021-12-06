package com.zss.springboot.conditional.custom.service;

public class UnixStorage implements SystemStorage{
    @Override
    public String loadStorage() {
        return "unix";
    }
}
