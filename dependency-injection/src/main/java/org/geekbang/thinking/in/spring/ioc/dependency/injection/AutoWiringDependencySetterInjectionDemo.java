package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Autowiring 依赖 Setter 方法注入示例
 *
 * @author fangkuangzhang
 * @date 2021/7/26 17:23
 */
public class AutoWiringDependencySetterInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String xmlLocation = "classpath:/META-INF/autowiring-dependency-setter-injection.xml";
        beanDefinitionReader.loadBeanDefinitions(xmlLocation);

        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println("UserHolder: " + userHolder);
    }

}
