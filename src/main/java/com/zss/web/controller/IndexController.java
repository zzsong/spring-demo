package com.zss.web.controller;

import com.alibaba.fastjson.JSON;
import com.zss.web.domain.AuthParam;
import com.zss.web.domain.StreamParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {


    @RequestMapping("index/json")
    public String jsonRequest(@RequestBody StreamParam streamParam){
        System.out.println(">>>>>>>>>>>");
        System.out.println(JSON.toJSONString(streamParam));
//        System.out.println(JSON.toJSONString(authParam));
        return "";
    }

}
