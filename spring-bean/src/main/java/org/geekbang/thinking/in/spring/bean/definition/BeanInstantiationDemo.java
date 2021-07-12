package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean实例化示例
 *
 * @author fangkuangzhang
 * @date 2021/7/11 16:45
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");

        // 1.使用静态方法创建Bean
        User userByStaticMethod = beanFactory.getBean("user-by-static-method", User.class);
        System.out.println("create user by static method: " + userByStaticMethod);

        // 2.使用实例的方法创建Bean
        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        System.out.println("create user by instance method: " + userByInstanceMethod);

        // 3.使用FactoryBean创建Bean
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println("create user by factory bean: " + userByFactoryBean);

        System.out.println(userByStaticMethod == userByInstanceMethod);
        System.out.println(userByStaticMethod == userByFactoryBean);
    }

}
