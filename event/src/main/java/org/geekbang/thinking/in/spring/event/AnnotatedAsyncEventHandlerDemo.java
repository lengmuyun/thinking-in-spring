package org.geekbang.thinking.in.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 注解驱动异步事件处理器示例
 */
@EnableAsync
public class AnnotatedAsyncEventHandlerDemo {

    public static void main(String[] args) {
        // 创建注解驱动 Spring 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class
        context.register(AnnotatedAsyncEventHandlerDemo.class);

        // 启动 Spring 应用上下文
        context.refresh();

        context.publishEvent(new MySpringEvent("Hello,World"));

        // 关闭 Spring 应用上下文
        context.close();
    }

    @Async
    @EventListener
    public void applicationListener(MySpringEvent event) {
        System.out.printf("[线程 ： %s] 监听到事件 : %s\n", Thread.currentThread().getName(), event);
    }

    @EventListener
    public void syncApplicationListener(MySpringEvent event) {
        System.out.printf("[线程 ： %s] 监听到同步事件 : %s\n", Thread.currentThread().getName(), event);
    }

    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadExecutor();
    }

}
