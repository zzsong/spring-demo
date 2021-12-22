package com.zss.extension.BeanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


/**
 * 实现这些方法来提供您自己的（或覆盖容器的默认值）实例化逻辑、依赖项解析逻辑等。
 * 如果要在 Spring 容器完成对 bean 的实例化、配置和初始化之后实现一些自定义逻辑，可以插入一个或多个自定义BeanPostProcessor实现。
 *
 *
 *ApplicationContext 可以在其 Bean 定义中自动检测 BeanPostProcessor Bean，并将这些后处理器应用于随后创建的任何 Bean。
 * 普通的BeanFactory允许对后处理器进行编程注册，将它们应用于通过Bean工厂创建的所有Bean。
 *
 * BeanPostProcessor实例的范围是每个容器。
 * 它只会对该容器中的 bean 进行后处理。 在一个容器中定义的 bean 不会被另一个容器中定义的 bean 进行后处理BeanPostProcessor，即使两个容器都是同一层次结构的一部分。
 *
 *
 * 将回调接口或注解与自定义BeanPostProcessor 实现结合使用是扩展 Spring IoC 容器的常用方法。一个例子是 Spring 的AutowiredAnnotationBeanPostProcessor
 *
 * 利用 Spring 上下文生命周期，并在处理 Bean 时与 Bean 进行交互。
 *
 */
public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor {

    /**
     *在任何 Bean 初始化回调之前将此 BeanPostProcessor 应用于给定的新 Bean 实例（如初始化 Bean 的 AfterPropertiesSet 或自定义 init 方法）。
     * Bean 将已使用属性值填充。返回的 Bean 实例可能是原始实例周围的包装器。 默认实现按原样返回给定的 Bean。
     *
     */

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean before");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Bean '" + beanName + "' created : " + bean.toString());
        return bean;
    }
}
