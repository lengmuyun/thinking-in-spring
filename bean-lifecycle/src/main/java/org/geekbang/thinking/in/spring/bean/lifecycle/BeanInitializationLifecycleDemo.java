package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * Bean 初始化生命周期示例
 *
 * @author fangkuangzhang
 * @date 2022/1/4 8:30
 */
public class BeanInitializationLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        int beansNumber = beanDefinitionReader.loadBeanDefinitions("META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml");
        System.out.println("beans number: " + beansNumber);
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

    public static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if ("superUser".equals(beanName) && SuperUser.class.equals(beanClass)) {
                return new SuperUser();
            }
            return null;
        }

    }

}