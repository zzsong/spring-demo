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
    /**
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

    /*
    final: 二处赋值，1、初始化赋值； 2、构造方法中赋值。

    ClassLoader cl = ClassUtils.class.getClassLoader();
	if (cl == null) //说明ClassUtils是根加载器
     */
    /*
    Aware 接口，特定的事件被发生时，Aware里面的方法被执行。
    BeanNameAware： 通常不建议一个对象依赖于它的bean名称，因为这表示对外部配置的潜在脆弱依赖，以及对Spring API的可能不必要的依赖。
    在创建此bean的bean工厂中设置bean的名称。
在填充普通bean属性之后，但在初始化回调(如InitializingBean.afterPropertiesSet()或自定义初始化方法)之前调用。
参数:
name—工厂中bean的名称。注意，这个名称是工厂中使用的实际bean名称，它可能与最初指定的名称不同:特别是对于内部bean名称，实际bean名称可能通过附加“#…”后缀而变得惟一。
如果需要，可以使用beanfactoryutil . originalbeanname(字符串)方法提取原始bean名称(不带后缀)。

     */


    /**
    environment： 表示当前运行时的环境。
     可以处理二件事： profiles and properties

     */

    public static void main(String[] args) {
        //第一阶段：bean的解析和工厂装配
        //资源（指定的类路径下的，指定XML，指定文件系统当中等（properties\yml\xml\....） 统称为资源）
        /*
        使用 ClassLoader 加载
         */
        Resource resource = new ClassPathResource("kernel-config/applicationContext.xml");
        //创建对象工厂
        //对象工厂目的：解放使用者或消费端。对于使用者而言，无需再使用显示的new对象。而是通过工厂拿出来（取对象）。这些对象由工厂来完成创建。
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //读取器（不同资源，需要使用不同的读取器进行解析）
        //通过读取器，让资源与工厂建立关联。读取资源完成放入到工厂当中，由工厂统一来管理
        //读取器目标：从bean配置信息中把一个一个bean读取出来，构造bean实例，托管给工厂管理。
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        //进入解析、装配成bean、把bean放入工厂统一管理
        /**
        XmlBeanDefinitionReader 328： 解决A.xml import B.xml 同时在B.xml文件中import A.xml 循环加载
        if (!currentResources.add(encodedResource)) {
			throw new BeanDefinitionStoreException(
					"Detected cyclic loading of " + encodedResource + " - check your import definitions!");
		}

         ThreadLocal： 内部是使用弱引用，用完后必需remove(),不然可能会导致内存泄漏。
         */
        /**
         * xml两种解析方式： SAX 、DOM
         * DOM： 将整个XML文件读取到内存当中，在内存中构建整个的XML 树型结构（节点、节点属性等），占内存。文档驱动
         *
         * SAX： 以流的方式对XML进行读取。遇到标签即进行解析。事件驱动

         */
        beanDefinitionReader.loadBeanDefinitions(resource);

        //第二阶段：从bean工厂加载、获取bean
        //使用String参数，必需指定ID或name，否则异常：
        //NoSuchBeanDefinitionException: No bean named 'student' available
//        Student stu = (Student) beanFactory.getBean("student");
//        System.out.println("name:"+stu.getName()+" \t age:"+stu.getAge());
        //这样写法有其风险，如果出现重复beanName会抛异常出现
        Student student = beanFactory.getBean(Student.class);
        System.out.println("name:"+student.getName()+" \t age:"+student.getAge());
    }
}
