package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Bean 垃圾回收（GC）示例
 *
 * @author fangkuangzhang
 * @date 2021/7/15 7:39
 */
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class(配置类)
        applicationContext.register(BeanInitializationDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();
        // 关闭 Spring 应用上下文
        applicationContext.close();

        TimeUnit.SECONDS.sleep(10);
        System.gc();
        TimeUnit.SECONDS.sleep(10);
    }

}
