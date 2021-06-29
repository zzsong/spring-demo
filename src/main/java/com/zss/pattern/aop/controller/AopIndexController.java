package com.zss.pattern.aop.controller;

import com.zss.pattern.aop.manager.MarketCalcManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class AopIndexController {

    @Autowired
    private MarketCalcManager marketCalcManager;

    @RequestMapping("index")
    public String index(){
        marketCalcManager.runHk();

        marketCalcManager.runUs();
        return LocalDateTime.now().toString();
    }
}
