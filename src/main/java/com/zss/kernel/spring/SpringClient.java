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

     关于Spring Bean实例的注册流程：

     1、定义spring配置文件
     2、通过Resource对象将Spring配置文件进行抽象，抽象成一个具体的Resource对象（如ClassPathResource）；
     3、定义好将要使用的bean工厂（各种bean工厂）
     4、定义好XmlBeanDefinitionReader对象，并将工厂对象作为参数传递进去，从而构建好二者之间的关联关系。
     5、通过XmlBeanDefinitionReader对象读取之前所抽象出的Resource对象。
     6、流程开始进行解析。
     7、针对XML文件进行各种元素以及元素属性的解析，这里面，真正的解析是通过BeanDefinitionParserDelegate对象来完成的（委托模式）
     8、通过BeanDefinitionParserDelegate对象在解析文件时，又使用到了模板方法设计模式（pre , process, post）
     9、当所有的bean标签元素都解析完毕后，开始定义一个BeanDefinition对象，该对象是一个非常重要的对象，里面容纳了一个Bean相关的所有属性
     10、BeanDefinition对象创建完毕后，Spring又会创建一个BeanDefinitionHolder对象来持有这个BeanDefinition对象
     11、BeanDefinitionHolder对象主要包含两部分内容：beanName  和 BeanDefinition
     12、工厂会将解析出来的Bean信息存放到内部的一个ConcurrentHashMap中，该Map的key是beanName(唯一值)，value是BeanDefinition对象
     13、调用Bean解析完毕的触发动作，从而触发相应的监听器的方法的执行。（使用到了观察者模式）


     关于Spring Bean的创建流程：
     1、Spring所管理的Bean 实际上是缓存在一个ConcurrentHashMap中的（singletonObject对象中）。
     2、该对象本质上是一个key-value对的形式，key: bean的名字（id）,value：是一个Object对象，就是所创建的bean对象。
     3、在创建Bean之前，首先需要将将该Bean的创建标识指定好，表示该Bean已经或是即将被创建，目的是增强缓存的效率。
     4、根据Bean的Scope属性来确定当前这个Bean是一个singleton还是一个prototype的Bean，然后创建相应的对象。
     5、无论singleton还是prototype的Bean，其创建的过程是一致的。
     6、通过Java反射机制来创建Bean的实例，在创建之前需要检查构造方法的访问修饰符，如果不是public的，则会调用setAccessible(true)
        方法来突破Java的语法限制，使得可以通过非public构造方法来完成对象实例的创建。
     7、当对象创建完毕后，开始进行对象属性的注入。（按XML顺序进行载入）
     8、在对象属性注入的过程中，Spring除去使用之前通过BeanDefinition对象获取的Bean信息外，还会通过反射的方式获取到上面所创建的Bean中的
        真实属性信息（还包括一个class属性，表示该Bean所对应的Class类型）
     9、完成Bean属性的注入（或抛出异常）
     10、如果Bean是一个单例的，那么将所创建出来的Bean添加到singletonObject对象中（即缓存中），供程序后续再次使用。


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


    /*
    environment： 表示当前运行时的环境。
     可以处理二件事： profiles and properties

     bean的注入方式：1、使用set注入； 2、使用构造器注入（方法参数不易过多，容易使用出错）

     */
    /*
    singleton、 prototype    创建对象的流程是一致，只有是否放于缓存中的区别。
     */

    public static void main(String[] args) {
        //第一阶段：bean的解析和工厂装配
        //有什么信息就解析什么信息，不会去校验信息的合法性。解析过程，没有对象创建出来。
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
        /*
        XmlBeanDefinitionReader 328： 解决A.xml import B.xml 同时在B.xml文件中import A.xml 循环加载
        if (!currentResources.add(encodedResource)) {
			throw new BeanDefinitionStoreException(
					"Detected cyclic loading of " + encodedResource + " - check your import definitions!");
		}

         ThreadLocal： 内部是使用弱引用，用完后必需remove(),不然可能会导致内存泄漏。


         AbstractBeanDefinition:
         @Nullable
         private volatile Object beanClass; //代表二层含义：1、class对象， 2、能够实例化的bean的字符串表示。 显示使用Object属性。

         */
        /*
         * xml两种解析方式： SAX 、DOM
         * DOM： 将整个XML文件读取到内存当中，在内存中构建整个的XML 树型结构（节点、节点属性等），占内存。文档驱动
         *
         * SAX： 以流的方式对XML进行读取。遇到标签即进行解析。事件驱动

         */
        beanDefinitionReader.loadBeanDefinitions(resource);//执行完到此，XML中的Bean并没有被实例化。

        //第二阶段：从bean工厂加载、获取bean
        //使用String参数，必需指定ID或name，否则异常：
        //NoSuchBeanDefinitionException: No bean named 'student' available
        //name指定必需唯一（与ID一样作用）
        //id / name如果没有指定，则使用默认的className拼上# 再接计数器（计数从0开始）
        Student stu = (Student) beanFactory.getBean("com.zss.kernel.spring.bean.Student#1");
        System.out.println("name:"+stu.getName()+" \t age:"+stu.getAge());
        //这样写法有其风险，如果出现重复beanName会抛异常出现
//        Student student = beanFactory.getBean(Student.class);
//        System.out.println("name:"+student.getName()+" \t age:"+student.getAge());
    }
}
