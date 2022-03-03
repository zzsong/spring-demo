package com.zss.springboot;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/*
    JDWP：（Java Debug Wire Protocol）Java调试协议
    java -agentlib:jdwp=help  查看详细

    执行：  java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5050 -jar spring-boot-lecture-1.0-SNAPSHOT.jar

 */
/*
    @SpringBootConfiguration


    @Configuration
    创建@Configuration配置类的约束：
    1、必须作为类提供（即不是作为从工厂方法返回的实例），允许通过生成的子类增强运行时。
    2、必须是non-final的（允许在运行时使用子类），除非proxyBeanMethods标志设置为false，在这种情况下不需要运行时生成的子类。
    3、必须是non-local的（即不能在方法中声明）。
    4、任何嵌套的配置类都必须声明为静态的。

    @Bean方法可能不会反过来创建更多的配置类（任何这样的实例都将被视为常规Bean，其配置注释保持未被发现）

    @EnableWebMvc :
    将应用程序标记为web应用程序，并激活关键行为，如设置DispatcherServlet。Spring Boot看到时自动添加它

 */


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
public class SpringBootStart {

    public static void main(String[] args) {
        /**
         * 一、使用main方法 启用程序
         * classLoader: 	 sun.misc.Launcher$AppClassLoader@18b4aac2
         *
         * 二、使用命令启动运行： java -jar spring-boot-lecture-1.0-SNAPSHOT.jar
         * classLoader: 	 org.springframework.boot.loader.LaunchedURLClassLoader@685f4c2e
         *
         */
        System.out.println("classLoader: \t "+SpringBootStart.class.getClassLoader());
        SpringApplication.run(SpringBootStart.class, args);

//        //第二种，启动
//        SpringApplication application = new SpringApplication(SpringBootStartApp.class);
//        application.setBannerMode(Banner.Mode.CONSOLE);
//        application.setBanner(new CustomerBanner());//自定义Banner
//        application.run(args);
    }


}
