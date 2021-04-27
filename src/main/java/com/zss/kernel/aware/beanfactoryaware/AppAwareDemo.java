package com.zss.kernel.aware.beanfactoryaware;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppAwareDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/kernel-config/applicationContext-beanfactoryaware.xml");

        IPaymentService paymentService = (IPaymentService) context.getBean("paymentServiceBean");
        paymentService.performPayment("card");
        paymentService.performPayment("cash");

        System.out.println(".....finish !");
    }
}
