package com.zss.web.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private Logger log = LoggerFactory.getLogger(getClass());


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
    }

    @Override
    public  void addInterceptors(InterceptorRegistry registry) {
        //不拦截首页/,  拦截所有其它请求
        List<String> excludePath = new ArrayList<>();
//        excludePath.add("/cp/**");
        excludePath.add("/favicon.ico");
        excludePath.add("/error");
        excludePath.add("/");

        excludePath.add("/swagger-ui.html/**");
        excludePath.add("/webjars/**");
        excludePath.add("/swagger-resources/**");
        excludePath.add("/v2/**");
        excludePath.add("/api-docs/**");

    }

    /**
     //     * swagger-ui.html路径映射，浏览器中使用/api-docs访问
     //     * @param registry
     //     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/api-docs","/swagger-ui.html");
    }

}
