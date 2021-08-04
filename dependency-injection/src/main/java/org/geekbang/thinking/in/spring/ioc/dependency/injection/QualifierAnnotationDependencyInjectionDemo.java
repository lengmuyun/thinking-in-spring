package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * {@link org.springframework.beans.factory.annotation.Qualifier} 注解依赖注入
 *
 * @author fangkuangzhang
 * @date 2021/8/4 7:55
 */
@Configuration
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User user1;

    @Autowired
    private Collection<User> users;

    @Autowired
    @Qualifier
    private Collection<User> qualifiedUsers;

    @Autowired
    @UserGroup
    private Collection<User> groupedUsers;

    @Bean
    @Qualifier
    public User user1() {
        return new User(7L);
    }

    @Bean
    @Qualifier
    public User user2() {
        return new User(8L);
    }

    @Bean
    @UserGroup
    public User user3() {
        return new User(9L);
    }

    @Bean
    @UserGroup
    public User user4() {
        return new User(10L);
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        // 期待super user
        System.out.println("demo.user: " + demo.user);
        // 期待user
        System.out.println("demo.user1: " + demo.user1);
        // 期待super user + user
        System.out.println("demo.users: " + demo.users);
        // 期待user1 + user2 + user3 + user4
        System.out.println("demo.qualifiedUsers: " + demo.qualifiedUsers);
        // 期待user3 + user4
        System.out.println("demo.groupedUsers: " + demo.groupedUsers);

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

}
