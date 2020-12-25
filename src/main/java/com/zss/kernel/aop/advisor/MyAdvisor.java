package com.zss.kernel.aop.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyAdvisor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before advisor invoke>>>>>>>");

        Object result = invocation.proceed();

        System.out.println("after advisor invoke<<<<<<<<");
        return result;
    }
}
