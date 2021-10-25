package com.zss.lifecycle;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
public class LifecycleBootstrap {

    /**
     * 加载顺序：
     *  构造函数 》 PostConstruct 》InitializingBean 》 配置init-method 》 BeanPostProcessor
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(LifecycleBootstrap.class);
    }
}
