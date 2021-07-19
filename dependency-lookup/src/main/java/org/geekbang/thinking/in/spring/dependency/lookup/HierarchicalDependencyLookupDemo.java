package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找示例
 *
 * @author fangkuangzhang
 * @date 2021/7/19 7:36
 */
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class(配置类)
        applicationContext.register(ObjectProviderDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();

        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("Parent BeanFactory: " + beanFactory.getParentBeanFactory());

        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("Parent BeanFactory: " + parentBeanFactory);

        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(parentBeanFactory, "user");

//        displayContainsBean(beanFactory, "user");
//        displayContainsBean(parentBeanFactory, "user");

        displayContainsBean(beanFactory, "helloWorld");
        displayContainsBean(parentBeanFactory, "helloWorld");

        // 关闭 Spring 应用上下文
        applicationContext.close();
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Bean[name : %s] : %s\n", beanFactory, beanName,
                containsBean(beanFactory, beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(parentHierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前 BeanFactory[%s] 是否包含 Local Bean[name : %s] : %s\n", beanFactory, beanName,
                beanFactory.containsLocalBean(beanName));
    }

    private static HierarchicalBeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        return beanFactory;
    }

}
