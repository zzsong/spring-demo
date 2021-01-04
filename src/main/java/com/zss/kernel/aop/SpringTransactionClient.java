package com.zss.kernel.aop;

import com.zss.kernel.aop.transaction.domain.Student;
import com.zss.kernel.aop.transaction.service.StudentService;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class SpringTransactionClient {

    /**
     *
    针对于数据库事务的操作：


     */

    public static void main(String[] args) {
        Resource resource = new ClassPathResource("kernel-config/applicationContext-aop-transaction.xml");
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);
        definitionReader.loadBeanDefinitions(resource);

        StudentService studentService = (StudentService) beanFactory.getBean("baseTransactionProxy");

        Student student = new Student();
        student.setStuName("admin");
        student.setStuAge(20);

        studentService.insertStudent(student);

    }
}
