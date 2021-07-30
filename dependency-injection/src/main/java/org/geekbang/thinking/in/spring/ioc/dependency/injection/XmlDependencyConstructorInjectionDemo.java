package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于 XML 资源的依赖 Constructor 注入示例
 *
 * @author fangkuangzhang
 * @date 2021/7/31 7:24
 */
public class XmlDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String xmlLocation = "classpath:/META-INF/dependency-constructor-injection.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlLocation);

        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println("UserHolder: " + userHolder);
    }

}
