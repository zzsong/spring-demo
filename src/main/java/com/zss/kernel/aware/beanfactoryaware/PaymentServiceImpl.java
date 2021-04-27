package com.zss.kernel.aware.beanfactoryaware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 由希望知道其拥有BeanFactory的bean实现。
 *
 * 例如，bean可以通过工厂(依赖查找)查找协作bean。请注意，大多数bean将选择通过相应的bean属性或构造函数参数(依赖注入)接收对协作bean的引用。
 *
 * setBeanFactory(BeanFactory beanFactory)
 *
 * 向bean实例提供所属工厂的回调。
 *
 * 在填充普通bean属性之后，但在初始化回调(如InitializingBean.afterPropertiesSet()或自定义初始化方法之前调用。
 *
 * **让Bean获取配置他们的BeanFactory的引用。**
 */
public class PaymentServiceImpl implements IPaymentService, BeanFactoryAware {

    private BeanFactory beanFactory;

    private PayService payService;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void performPayment(String paymentType) {
        System.out.println("performPayment Method called");
        payService = (PayService) beanFactory.getBean(paymentType);
        payService.executePayment();
    }
}
