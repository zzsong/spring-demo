package com.zss.springboot.retry;

import com.zss.springboot.retry.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class RetryController {

    @Autowired
    private RetryService retryService;

    @RequestMapping("retry")
    public String retryIndex(){
        try {
            retryService.example01();
        } catch (IllegalAccessException e) {
            System.out.println("controller  exception..............................");
            e.printStackTrace();
        }
        return LocalDateTime.now().toString();
    }

}
