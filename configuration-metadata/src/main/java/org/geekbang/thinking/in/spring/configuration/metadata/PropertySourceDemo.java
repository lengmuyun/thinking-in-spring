package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * 外部化配置示例
 *
 * @author fangkuangzhang
 * @date 2022/2/2 12:48
 */
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
public class PropertySourceDemo {

    /**
     * user.name 是 Java Properties 默认存在，当前用户：q，而非配置文件中定义"方"
     *
     * @param id
     * @param userName
     * @return
     */
    @Bean
    public User customizedUser(@Value("${user.id}") Long id, @Value("${user.name}") String userName) {
        User user = new User();
        user.setId(id);
        user.setName(userName);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 扩展 Environment 中的 PropertySources
        // 添加 PropertySource 操作必须在 refresh 方法之前完成
        HashMap<String, Object> source = new HashMap<>();
        source.put("user.name", "kkkk");
        org.springframework.core.env.PropertySource propertySource = new MapPropertySource("prop-source", source);
        context.getEnvironment().getPropertySources().addFirst(propertySource);
        // 注册当前类作为 Configuration Class
        context.register(PropertySourceDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();
        Map<String, User> userMap = context.getBeansOfType(User.class);
        userMap.forEach((k, v) -> System.out.printf("bean name: %s, value: %s\n", k, v));
        // 关闭 Spring 应用上下文
        context.close();
    }

}
