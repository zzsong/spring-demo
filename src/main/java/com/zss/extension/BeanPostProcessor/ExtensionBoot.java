package com.zss.extension.BeanPostProcessor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExtensionBoot {

    /**
     *
     * BeanPostProcessor.before 》 InitializingBean  afterPropertiesSet 》 xml配置initMethod 》BeanPostProcessor.after
     *
     *
     */

    public static void main(final String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("extension/beans.xml");
        Messenger messenger = ctx.getBean("messenger", Messenger.class);
        System.out.println(messenger);
    }

}
