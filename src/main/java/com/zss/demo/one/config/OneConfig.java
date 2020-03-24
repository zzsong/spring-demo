package com.zss.demo.one.config;

import com.zss.demo.one.service.OrderService;
import com.zss.demo.one.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 使用@Configuration，
 * OneConfig表示被CGLIB代理：com.zss.demo.one.config.OneConfig$$EnhancerBySpringCGLIB$$1d394961@a227af
 * 保证一个实例：
 *      @Bean
 *     public OrderService orderService(){
 *         userService();
 *         return new OrderService();
 *     }
 *     只把userService()打印一次。
 *
 * 如果不使用@Configuration
 * OneConfig 表示自身对象：com.zss.demo.one.config.OneConfig@11f8236
 *   @Bean
 *  *     public OrderService orderService(){
 *  *         userService();
 *  *         return new OrderService();
 *  *     }
 *  *     只把userService()打印二次。
 *
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.zss.demo.one"})
public class OneConfig {

    @Bean
    public OrderService orderService(){
        userService();
        return new OrderService();
    }

    @Bean
    public UserService userService(){
        System.out.println("=========user=============");
        return new UserService();
    }
}
