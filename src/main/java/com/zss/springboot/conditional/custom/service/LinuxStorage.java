package com.zss.springboot.conditional.custom.service;

public class LinuxStorage implements SystemStorage{
    @Override
    public String loadStorage() {
        return "Linux";
    }
}
