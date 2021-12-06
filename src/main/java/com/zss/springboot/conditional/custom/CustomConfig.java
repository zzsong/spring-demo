package com.zss.springboot.conditional.custom;

import com.zss.springboot.conditional.custom.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfig {

    @Bean
    @Conditional(LinuxStorageConditional.class)
    public SystemStorage linuxStorage(){
        return new LinuxStorage();
    }

    @Bean
    @Conditional(UnixStorageConditional.class)
    public SystemStorage unixStorage(){
        return new UnixStorage();
    }

    @Bean
    @Conditional(WindowsStorageConditional.class)
    public SystemStorage widowsStorage(){
        return new WindowsStorage();
    }

    @Bean
    @Conditional(MacOsStorageConditional.class)
    public SystemStorage macStorage(){
        return new MacStorage();
    }


}
