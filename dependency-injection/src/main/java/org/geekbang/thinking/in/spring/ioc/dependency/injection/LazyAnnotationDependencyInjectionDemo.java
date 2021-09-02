package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * {@link Qualifier} 实现延迟依赖注入
 *
 * @author fangkuangzhang
 * @date 2021/8/4 7:55
 */
@Configuration
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // 实时注入

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Autowired
    private ObjectFactory<Set<User>> userObjectFactory;

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class（配置类） -> Spring Bean
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载 XML 资源，解析并且生成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动 Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        // 期待super user
        System.out.println("demo.user: " + demo.user);
        // 期待user、superUser
        System.out.println("userObjectProvider: " + demo.userObjectProvider.getIfAvailable());
        // 期待user、superUser
        System.out.println("userObjectFactory: " + demo.userObjectFactory.getObject());

        System.out.println("==================开始打印ObjectProvider的集合元素==================");
        demo.userObjectProvider.forEach(System.out::println);
        System.out.println("==================结束打印ObjectProvider的集合元素==================");

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

}
