package com.zss.extension.BeanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 允许自定义修改应用程序上下文的 Bean 定义的工厂挂钩，调整上下文的基础 Bean 工厂的 Bean 属性值。
 * 对于面向系统管理员的自定义配置文件非常有用，这些文件会覆盖在应用程序上下文中配置的 Bean 属性。
 *
 * BeanFactoryPostProcessor 可以与 Bean 定义交互和修改，但永远不会与 Bean 实例交互和修改。这样做可能会导致过早的 Bean 实例化，违反容器并导致意外的副作用。
 * 如果需要 Bean 实例交互，请考虑改为实现 BeanPostProcessor。
 *
 *
 * 如果要更改实际 bean 实例（即从配置元数据创建的对象），则需要使用  BeanPostProcessor （在前面通过使用自定义 Bean 中BeanPostProcessor描述）。
 * 虽然技术上可以在使用 bean 实例BeanFactoryPostProcessor（例如，通过使用 BeanFactory.getBean()），
 * 但这样做会导致过早的 bean 实例化，违反标准容器生命周期。这可能会导致负面影响，例如绕过 bean 后处理。
 *
 * ApplicationContext 在其 Bean 定义中自动检测 BeanFactoryPostProcessor Bean，并在创建任何其他 Bean 之前应用它们。
 *
 * BeanFactoryPostProcessor对 bean 配置元数据进行操作。也就是说，Spring IoC 容器允许BeanFactoryPostProcessor读取配置元数据并在容器实例化除实例以外的任何 bean之前可能更改它。
 *
 * BeanFactoryPostProcessor是针对于bean容器的，在调用它时，BeanFactory只加载了bean的定义，还没有对它们进行实例化
 *
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    /**
     * 在应用程序上下文的标准初始化后修改其内部 Bean 工厂。所有 bean 定义都将已加载，但尚未实例化任何 bean。这允许重写或添加属性，甚至添加到预先初始化的 bean 中。
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println(">>>>> postProcessBeanFactory");
    }
}
