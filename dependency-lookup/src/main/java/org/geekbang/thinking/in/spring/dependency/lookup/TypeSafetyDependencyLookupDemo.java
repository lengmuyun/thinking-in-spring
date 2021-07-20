package org.geekbang.thinking.in.spring.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.function.Consumer;

/**
 * 类型安全的依赖查找
 *
 * @author fangkuangzhang
 * @date 2021/7/21 7:09
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class(配置类)
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();

        displayBeanFactoryGetBean(applicationContext);
        displayObjectFactoryGetObject(applicationContext);
        displayObjectProviderIfAvailable(applicationContext);
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        displayObjectProviderStreamOps(applicationContext);

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    /**
     * 演示 {@link ObjectProvider#forEach(Consumer)} 方法的安全性
     * @param applicationContext
     */
    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps", () -> objectProvider.forEach(System.out::println));
    }

    /**
     * 演示 {@link ListableBeanFactory#getBeansOfType(Class)} 方法的安全性
     * @param applicationContext
     */
    private static void displayListableBeanFactoryGetBeansOfType(AnnotationConfigApplicationContext applicationContext) {
        ListableBeanFactory listableBeanFactory = applicationContext.getDefaultListableBeanFactory();
        printBeansException("displayListableBeanFactoryGetBeansOfType", () -> listableBeanFactory.getBeansOfType(User.class));
    }

    /**
     * 演示 {@link ObjectProvider#getIfAvailable()} 方法的安全性
     * @param applicationContext
     */
    private static void displayObjectProviderIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> objectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderIfAvailable", objectProvider::getIfAvailable);
    }

    /**
     * 演示 {@link ObjectFactory#getObject()} 方法的安全性
     * @param applicationContext
     */
    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        ObjectFactory<User> objectFactory = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", objectFactory::getObject);
    }

    /**
     * 演示 {@link BeanFactory#getBean(Class)} 方法的安全性
     * @param beanFactory
     */
    private static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }

    private static void printBeansException(String source, Runnable runnable) {
        System.err.println("==========================================");
        System.err.println("Source from :" + source);
        try {
            runnable.run();
        } catch (BeansException exception) {
            exception.printStackTrace();
        }
    }

}
