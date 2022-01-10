package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * Bean 初始化生命周期示例
 *
 * @author fangkuangzhang
 * @date 2022/1/8 20:52
 */
public class BeanInitializationLifecycleDemo {

    public static void main(String[] args) {
        executeBeanFactory();
    }

    private static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 添加CommonAnnotationBeanPostProcessor 解决@PostConstruct
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        int beansNumber = beanDefinitionReader.loadBeanDefinitions("META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml");
        System.out.println("beans number: " + beansNumber);

        beanFactory.preInstantiateSingletons();

        // 通过 Bean Id 和类型进行依赖查找
        User user = beanFactory.getBean("user", User.class);
        System.out.println("user: " + user);

        // 通过 Bean Id 和类型进行依赖查找
        SuperUser superUser = beanFactory.getBean("superUser", SuperUser.class);
        System.out.println("superUser: " + superUser);

        // 通过 Bean Id 和类型进行依赖查找
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println("userHolder: " + userHolder);
    }

}
