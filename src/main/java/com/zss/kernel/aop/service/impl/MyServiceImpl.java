package com.zss.kernel.aop.service.impl;

import com.zss.kernel.aop.service.MyService;

/*
目标对象
 */
public class MyServiceImpl implements MyService {

    @Override
    public void myMethod() {
        System.out.println("myServiceImpl myMethod invoked");
    }

}
