package com.zss.tomcat.config;

import com.zss.tomcat.SpringBootTomcatStrap;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
    打成war发布到tomcat服务器
 */
public class ServletInitializer  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(SpringBootTomcatStrap.class);
    }

}
