package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * Bean 元信息配置示例
 *
 * @author fangkuangzhang
 * @date 2021/12/12 19:06
 */
public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/user.properties";
        Resource resource = new ClassPathResource("/META-INF/user.properties");
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beansNumber = reader.loadBeanDefinitions(encodedResource);
        System.out.println("beans number: " + beansNumber);

        User user = beanFactory.getBean("user", User.class);
        System.out.println("user: " + user);
    }

}
