package com.zss.kernel.proxy.dynamicproxy;

public class RealSubject implements Subject{
    @Override
    public void myRequest() {
        System.out.println("from real subject object");
    }
}
