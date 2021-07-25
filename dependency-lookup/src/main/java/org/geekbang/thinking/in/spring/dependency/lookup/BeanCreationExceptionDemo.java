package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link org.springframework.beans.factory.BeanCreationException} 示例
 *
 * @author fangkuangzhang
 * @date 2021/7/25 10:16
 */
public class BeanCreationExceptionDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Pojo.class);
        // 注册 BeanDefinition Bean Class 是一个 CharSequence 接口
        applicationContext.registerBeanDefinition("errorBean", builder.getBeanDefinition());
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    static class Pojo implements InitializingBean {

        @PostConstruct
        public void init() throws Exception {
            throw new Exception("PostConstruct....");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("InitializingBean#afterPropertiesSet....");
        }

    }

}
