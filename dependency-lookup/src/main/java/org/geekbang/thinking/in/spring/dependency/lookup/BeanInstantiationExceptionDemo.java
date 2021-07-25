package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * {@link org.springframework.beans.BeanInstantiationException} 示例
 *
 * @author fangkuangzhang
 * @date 2021/7/25 9:54
 */
public class BeanInstantiationExceptionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        // 注册 BeanDefinition Bean Class 是一个 CharSequence 接口
        applicationContext.registerBeanDefinition("errorBean", builder.getBeanDefinition());
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

}
