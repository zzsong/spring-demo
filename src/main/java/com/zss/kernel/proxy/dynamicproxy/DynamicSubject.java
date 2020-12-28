package com.zss.kernel.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicSubject implements InvocationHandler {

    private Object object;

    public DynamicSubject(Object object){
        this.object = object;
    }

    /**
     *
     * @param proxy     代理类的实例
     * @param method    目标对象所调用方法的Method对象
     * @param args      目标对象所调用方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("proxy: "+ proxy.getClass());
        System.out.println("Before >>"+method);

        //call target object method
        //调用目标对象的特定方法
        method.invoke(object, args);

        System.out.println("After >>"+method);
        //方法是无返回值。
        return null;
    }
}
