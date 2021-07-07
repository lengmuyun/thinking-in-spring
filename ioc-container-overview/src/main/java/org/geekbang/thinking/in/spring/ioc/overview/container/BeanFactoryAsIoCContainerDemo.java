package org.geekbang.thinking.in.spring.ioc.overview.container;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author fangkuangzhang
 * @date 2021/7/7 7:40
 */
public class BeanFactoryAsIoCContainerDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        int beanDefinitionCount = reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        System.out.printf("加载bean个数: " + beanDefinitionCount);

        lookupCollectionByType(beanFactory);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory lbf = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = lbf.getBeansOfType(User.class);
            System.out.println("查找到的所有的 User 集合对象：" + userMap);
        }
    }

}
