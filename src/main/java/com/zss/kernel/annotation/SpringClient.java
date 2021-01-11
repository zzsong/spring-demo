package com.zss.kernel.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringClient {

    /**
    基于注解与基于XML配置的不同：
     1、基于XML配置方式，Bean对象的创建是在程序首次从工厂中获取对象时才创建。
     2、基于注解配置方式，Bean对象的创建是在注解处理器解析相应的@Bean注解时调用了该注解所修饰的方法，当该方法执行后，相应的对象自然就已经被创建出来了，
        这时，Spring就会将该对象纳入到工厂的管理范围之内，当我们首次尝试从工厂中获取该Bean对象时，这时，该Bean对象实际上已经完成了创建并被纳入到工厂的管理范围之内

     */

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(PersonConfiguration.class);
        System.out.println(" register");
        applicationContext.refresh();
        System.out.println("对象创建完成，并工厂管理");

        PersonConfiguration personConfiguration = (PersonConfiguration)applicationContext.getBean("personConfiguration");
        //PersonConfiguration$$EnhancerBySpringCGLIB$$5275cc1
        //返回由CGLIB代理子类型生成。
        System.out.println(personConfiguration.getClass());

        //
        Person person = (Person)applicationContext.getBean("person");
        System.out.println(person.getName()+" \t  age:"+person.getAge());
    }
}
