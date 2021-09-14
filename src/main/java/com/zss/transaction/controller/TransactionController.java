package com.zss.transaction.controller;

import com.zss.transaction.manager.TransactionExampleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TransactionController {

    @Autowired
    private TransactionExampleManager transactionExampleManager;

    @RequestMapping("example/{version}")
    public String example(@PathVariable("version") int version, int id){

        switch (version){
            case 1: transactionExampleManager.example1();break;
            case 2: transactionExampleManager.example2();break;
            case 3: transactionExampleManager.example3(id);break;
        }
        System.out.println("-=================normal finish-=====");
        return LocalDateTime.now().toString();
    }
}
