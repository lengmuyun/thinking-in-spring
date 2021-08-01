package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于 Java 注解的依赖方法注入示例
 *
 * @author fangkuangzhang
 * @date 2021/8/2 7:39
 */
public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder;

    private static UserHolder staticUserHolder;

    private UserHolder userHolder2;

    @Autowired
    public void initUserHolder(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Autowired
    public static void initStaticUserHolder(UserHolder staticUserHolder) {
        AnnotationDependencyMethodInjectionDemo.staticUserHolder = staticUserHolder;
    }

    @Resource
    public void initUserHolder2(UserHolder userHolder2) {
        this.userHolder2 = userHolder2;
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);

        UserHolder userHolder = demo.userHolder;
        System.out.println("userHolder: " + userHolder);
        System.out.println("userHolder2: " + demo.userHolder2);
        System.out.println("staticUserHolder: " + demo.staticUserHolder);

        System.out.println(userHolder == demo.userHolder2);

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }
}
