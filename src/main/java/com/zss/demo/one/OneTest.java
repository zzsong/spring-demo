package com.zss.demo.one;


import com.zss.demo.one.config.OneConfig;
import com.zss.demo.one.interceptor.OneMethodInterceptor;
import com.zss.demo.one.service.OrderService;
import org.springframework.cglib.core.SpringNamingPolicy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OneTest {

    public static void main(String[] args) {

        interceptor();

    }

    public static void one(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(OneConfig.class);
        System.out.println(context.getBean(OneConfig.class));
        OrderService orderService = context.getBean(OrderService.class);
        orderService.query();

        System.out.println("======end===========");
    }

    public static void interceptor(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(OneConfig.class);
        System.out.println(context.getBean(OneConfig.class));

        Enhancer enhancer = new Enhancer();
        //增强父类
        enhancer.setSuperclass(OrderService.class);
        enhancer.setNamingPolicy(SpringNamingPolicy.INSTANCE);
        //过滤方法
        enhancer.setCallback(new OneMethodInterceptor());

        OrderService orderService = (OrderService) enhancer.create();
        orderService.query();

        System.out.println("======end===========");
    }
}
