package org.geekbang.thinking.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * {@link org.springframework.context.annotation.Profile} 示例
 */
@Configuration
public class ProfileDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(ProfileDemo.class);

        // 获取 Environment 对象 (可配置的)
        ConfigurableEnvironment environment = context.getEnvironment();
        // 默认 Profile = ["odd"] (当配置多个默认profile, 其获取bean的行为跟bean的注册顺序有关?)
        environment.setDefaultProfiles("odd");

        // 增加活跃的 Profile
        environment.addActiveProfile("even");

        // 启动 Spring 应用上下文
        context.refresh();

        Integer number = context.getBean("number", Integer.class);
        System.out.println(number);

        // 关闭 Spring 应用上下文
        context.close();
    }

    @Profile("even")
    @Bean("number")
    public Integer even() {
        return 2;
    }

//    @Profile("odd")
    @Bean("number")
    @Conditional(EvenProfileCondition.class)
    public Integer odd() {
        return 1;
    }

}
