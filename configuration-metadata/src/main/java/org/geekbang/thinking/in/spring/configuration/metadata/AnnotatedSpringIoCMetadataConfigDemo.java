package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * 基于 Java 注解 Spring IoC 容器元信息配置示例
 *
 * @author fangkuangzhang
 * @date 2022/1/31 20:53
 */
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/user-bean-definitions.properties") // Java 8+ @Repeatable 支持
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
public class AnnotatedSpringIoCMetadataConfigDemo {

    @Bean
    public User customizedUser(@Value("${user.id}") Long id, @Value("${user.name}") String userName) {
        User user = new User();
        user.setId(id);
        user.setName(userName);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类作为 Configuration Class
        context.register(AnnotatedSpringIoCMetadataConfigDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();
        Map<String, User> userMap = context.getBeansOfType(User.class);
        userMap.forEach((k, v) -> System.out.printf("bean name: %s, value: %s\n", k, v));
        // 关闭 Spring 应用上下文
        context.close();
    }

}
