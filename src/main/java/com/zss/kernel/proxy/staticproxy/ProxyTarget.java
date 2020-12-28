package com.zss.kernel.proxy.staticproxy;

/*
代理对象 也必需实现目标对象的接口

静态代理

缺点：
    每个代理对象都需要去关联目标对象

 */
public class ProxyTarget implements Target{

    private RealTarget realTarget;

    @Override
    public void myRequest() {

        beforeRequest();

        if (realTarget == null){
            realTarget = new RealTarget();
        }
        realTarget.myRequest();

        afterRequest();
    }

    private void beforeRequest(){
        System.out.println("proxy Before request");
    }

    private void afterRequest(){
        System.out.println("proxy After request");
    }
}
