package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过 {@link org.springframework.beans.factory.ObjectProvider} 进行依赖查找
 *
 * @author fangkuangzhang
 * @date 2021/7/17 12:17
 */
public class ObjectProviderDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class(配置类)
        applicationContext.register(ObjectProviderDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        lookupByObjectProvider(applicationContext);
        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    @Bean
    public String helloWorld() {
        return "Hello, World!";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println("string value: " + beanProvider.getObject());
    }

}
