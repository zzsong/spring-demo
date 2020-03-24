package com.zss.demo.one.service;

import java.time.LocalDateTime;


public class UserService implements CoreService {
    @Override
    public void query() {
        System.out.println("=========user======="+ LocalDateTime.now());
    }
}
