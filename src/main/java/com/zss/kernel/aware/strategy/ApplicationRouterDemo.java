package com.zss.kernel.aware.strategy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRouterDemo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/kernel-config/applicationContext-strategy.xml");

        PaymentRouter router = (PaymentRouter) context.getBean("paymentRouter");
        router.getPayService("cash").executePayment();
        router.getPayService("card").executePayment();
        System.out.println("......finish!");
    }
}
