package org.geekbang.thinking.in.spring.ioc.overview.container;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author fangkuangzhang
 * @date 2021/7/7 7:43
 */
@Configuration
public class AnnotationApplicationContextAsIoCContainerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);
        applicationContext.refresh();

        lookupCollectionByType(applicationContext);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory lbf = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = lbf.getBeansOfType(User.class);
            System.out.println("查找到的所有的 User 集合对象：" + userMap);
        }
    }

    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("fkz");
        return user;
    }

}
