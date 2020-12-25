package com.zss.kernel.aop;

import com.zss.kernel.aop.service.MyService;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class SpringClientAop {

    /**

     BeanFactory是Spring Ioc的工厂，它里面管理着Spring所创建出来的各种Bean对象，当我们在配置文件（注解）中声明了某个bean的Id后，
     通过这个ID就可以获取到与该ID所对应的class对象的实例（可能新建，也可能从缓存中获取）


     FactoryBean本质上也是一个Bean。 它同其他Bean一样，也是由BeanFactory所管理和维护的，当然它的实例也会缓存到Spring的工厂中（如果是单例）。
     它与普通的Bean的唯一区别在于，当Spring创建一个FactoryBean的实例后，它接下来会判断当前所创建的Bean是否是一个FactoryBean实例，
     如果不是，那么就直接将创建出来的Bean返回给客户端；
     如果是， 那么它会对其进行进一步的处理，根据配置文件所配置的target, advisor, interfaces等信息，在运行期间动态构建出来一个类，并生成该类的一个实例，
     最后将该实例返回给客户端。
     因此，我们在声明一个FactoryBean时，通过ID获取的并非这个FactoryBean的实例，而是它动态生成出来的一个代理实例（通过三种方式进行生成）


     * @param args
     */

    public static void main(String[] args) {
        Resource resource = new ClassPathResource("kernel-config/applicationContext-aop.xml");

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        beanDefinitionReader.loadBeanDefinitions(resource);

        MyService myService = (MyService) beanFactory.getBean("myAop");

        myService.myMethod();

        System.out.println(myService.getClass());
        System.out.println(myService.getClass().getSuperclass());
    }
}
