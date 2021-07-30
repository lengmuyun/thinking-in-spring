package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * "constructor" Autowiring 依赖构造器注入示例
 *
 * @author fangkuangzhang
 * @date 2021/7/31 7:30
 */
public class AutoWiringDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String xmlLocation = "classpath:/META-INF/autowiring-dependency-constructor-injection.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlLocation);

        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println("UserHolder: " + userHolder);
    }

}
