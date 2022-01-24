package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

/**
 * {@link PropertiesBeanDefinitionReader} 示例
 *
 * @author fangkuangzhang
 * @date 2022/1/24 8:43
 */
public class PropertiesBeanDefinitionReaderDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        ClassPathResource classPathResource = new ClassPathResource("/META-INF/user-bean-definitions.properties");
        EncodedResource resource = new EncodedResource(classPathResource, "UTF-8");
        int beanLoadCount = reader.loadBeanDefinitions(resource);
        System.out.printf("已加载 %d 个Bean Definition%n", beanLoadCount);

        User user = beanFactory.getBean("user", User.class);
        System.out.printf("user: %s", user);
    }

}
