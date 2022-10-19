package org.geekbang.thinking.in.spring.annotation;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * {@link org.springframework.beans.factory.config.PropertyPlaceholderConfigurer} 示例
 */
public class PropertyPlaceholderConfigurerDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/placeholder-resover.xml");

        User user = context.getBean("user", User.class);
        System.out.println("user: " + user);

        context.close();
    }

}
