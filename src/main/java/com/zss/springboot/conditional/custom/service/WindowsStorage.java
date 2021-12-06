package com.zss.springboot.conditional.custom.service;

public class WindowsStorage implements SystemStorage{
    @Override
    public String loadStorage() {
        return "windows";
    }
}
