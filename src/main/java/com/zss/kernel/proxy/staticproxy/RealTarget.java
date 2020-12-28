package com.zss.kernel.proxy.staticproxy;

public class RealTarget implements Target{
    @Override
    public void myRequest() {
        System.out.println("from RealTraget");
    }
}
