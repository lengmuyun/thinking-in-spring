package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link org.springframework.beans.factory.NoUniqueBeanDefinitionException} 示例
 *
 * @author fangkuangzhang
 * @date 2021/7/25 10:20
 */
public class NoUniqueBeanDefinitionExceptionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class(配置类)
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        try {
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf(" Spring 应用上下文存在%d个 %s 类型的 Bean，具体原因：%s%n",
                    e.getNumberOfBeansFound(),
                    String.class.getName(),
                    e.getMessage());
        }
        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    @Bean
    public String bean1() {
        return "Bean One";
    }

    @Bean
    public String bean2() {
        return "Bean Two";
    }

    @Bean
    public String bean3() {
        return "Bean Three";
    }

}
