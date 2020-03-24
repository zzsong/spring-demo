package com.zss.demo.one.service;

import java.time.LocalDateTime;


public class OrderService implements CoreService {
    @Override
    public void query() {
        System.out.println("=======OrderService========"+ LocalDateTime.now());
    }
}
