package com.zss.springboot.conditional.custom;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxStorageConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String storage = context.getEnvironment().getProperty("custom.storage");
        if ("linux".equals(storage) || "".equals(storage)){
            return true;
        }
        return false;
    }
}
