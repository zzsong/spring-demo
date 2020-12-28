package com.zss.kernel.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicSubjectClient {

    /*
    通过增加参数，可以把动态代理生成的字节码文件，生成到磁盘上。
    -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true

     */

    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();

        InvocationHandler invocationHandler = new DynamicSubject(realSubject);

        Class<?> classType = invocationHandler.getClass();

        //动态代理类所需要实现的接口，必需要跟目标对象实现相同接口。
        Subject subject = (Subject) Proxy.newProxyInstance(classType.getClassLoader()
                , realSubject.getClass().getInterfaces()
                , invocationHandler);

        subject.myRequest();

        //返回invoke方法中第一个参数Object proxy
        System.out.println(subject.getClass());

    }
}
