package com.zss.pattern.messagehandler.manager;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MessageHandlerContainer {

    private final Map<String, MessageHandler> handlerMap = new ConcurrentHashMap<>();

    @Autowired
    private List<MessageHandler> handlerList;

    @PostConstruct
    public void start(){
        Optional.ofNullable(handlerList).orElse(new ArrayList<>()).forEach(handler -> handlerMap.put(handler.getType(), handler));
    }

    public MessageHandler getHandler(String type){
        MessageHandler handler = handlerMap.get(type);
        if (handler == null){
            throw new IllegalArgumentException(String.format("未实现【%s】的MessageHandler业务功能",type));
        }
        return handler;
    }


    public Class<? extends Message> getMessageClass(MessageHandler handler){
        // 获得 Bean 对应的 Class 类名。因为有可能被 AOP 代理过。
        //确定给定 Bean 实例的最终目标类，不仅要遍历顶级代理，还要遍历任意数量的嵌套代理 — 尽可能长时间没有副作用，即仅针对单例目标。
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(handler);
        // 获得接口的 Type 数组
        /**
         * 返回类型，这些类型表示由此对象所表示的类或接口直接实现的接口
         * 如果超级接口是参数化类型，则为其返回的 Type 对象必须准确反映源代码中使用的实际类型参数。如果以前未创建过表示每个超级接口的参数化类型，则创建该类型。
         *
         * 如果此对象表示类，则返回值是一个数组，其中包含表示该类实现的所有接口的对象。数组中接口对象的顺序对应于接口名称在由此对象表示的类的声明的 implements 子句中的顺序。
         * 对于数组类，接口可克隆和可序列化按该顺序返回
         *
         *
         */
        Type[] interfaces = targetClass.getGenericInterfaces();

        Class<?> superclass = targetClass.getSuperclass();
        while ((Objects.isNull(interfaces) || 0 == interfaces.length) && Objects.nonNull(superclass)) { // 此处，是以父类的接口为准
            interfaces = superclass.getGenericInterfaces();
            superclass = targetClass.getSuperclass();
        }
        if (Objects.nonNull(interfaces)) {
            // 遍历 interfaces 数组
            for (Type type : interfaces) {
                // 要求 type 是泛型参数
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    // 要求是 MessageHandler 接口
                    if (Objects.equals(parameterizedType.getRawType(), MessageHandler.class)) {
                        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                        // 取首个元素
                        if (Objects.nonNull(actualTypeArguments) && actualTypeArguments.length > 0) {
                            return (Class<Message>) actualTypeArguments[0];
                        } else {
                            throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
                        }
                    }
                }
            }
        }
        throw new IllegalStateException(String.format("类型(%s) 获得不到消息类型", handler));
    }


}
