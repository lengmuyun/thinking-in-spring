package org.geekbang.thinking.in.spring.bean;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean别名示例
 *
 * @author fangkuangzhang
 * @date 2021/7/8 7:30
 */
public class BeanAliasDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-alias.xml");
        User fkzUser = beanFactory.getBean("fkz-user", User.class);
        User user = beanFactory.getBean("user", User.class);
        System.out.println("fkzUser == user: " + (fkzUser == user));
    }

}
