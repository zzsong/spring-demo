package com.zss.kernel.spring;

import com.zss.kernel.spring.bean.Student;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class SpringClient {

    //IoC 控制反转。本来由使用者来控制创建对象，转换成由工厂统一来控制创建，
    // 使用者无需关注对象是如何创建出来，只需要在使用时向工厂去要即可，工厂肯定可以传递返回一个正确的所需要对象。
    /*
    IoC（Inverse of Control, 控制反转）
    DI（Dependency Injections, 依赖注入） 当A对象包含一个属性声明B（即A包含B，当创建A实例时，B实例必需也要创建好，并且注入到A当中

    //>>
    关于Spring容器管理Bean的过程以及加载模式：
    1、需要将bean的定义信息声明在Spring 的配置文件当中。
    2、需要通过Spring抽象出的各种Resource来指定对应的配置文件
    3、需要显式声明一个Spring工厂，该工厂用来掌控我们在配置文件中所声明的各种bean 以及bean之间的依赖关系与注入关系
    4、需要定义一个信息读取器，该读取器用来读取之前所定义的bean配置文件信息
    5、读取器的作用是：读取我们所声明的配置文件信息，并且将读取器的信息装配到之前所声明的工厂当中
    6、需要将读取器与工厂以及资源对象进行相应的关联处理
    7、工厂所管理的全部对象装配完毕，可以供客户端直接调用，获取客户端想要使用的各种bean对象

    Spring对于Bean管理的核心组件：
    1、资源抽象
    2、工厂
    3、配置信息读取器（关联1、2结合）


    BeanFactory是Spring Bean 工厂最顶层的抽象

     */

    public static void main(String[] args) {
        //资源（指定的类路径下的，指定XML，指定文件系统当中等（properties\yml\xml\json....） 统称为资源）
        Resource resource = new ClassPathResource("kernel-config/applicationContext.xml");
        //创建对象工厂
        //对象工厂目的：解放使用者或消费端。对于使用者而言，无需再使用显示的new对象。而是通过工厂拿出来（取对象）。这些对象由工厂来完成创建。
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //读取器（不同资源，需要使用不同的读取器进行解析）
        //通过读取器，让资源与工厂建立关联。读取资源完成放入到工厂当中，由工厂统一来管理
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions(resource);

        //使用String参数，必需指定ID或name，否则异常：
        //NoSuchBeanDefinitionException: No bean named 'student' available
//        Student stu = (Student) beanFactory.getBean("student");
//        System.out.println("name:"+stu.getName()+" \t age:"+stu.getAge());
        Student student = beanFactory.getBean(Student.class);
        System.out.println("name:"+student.getName()+" \t age:"+student.getAge());
    }
}
