package org.geekbang.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@link ApplicationListener} 示例
 */
@EnableAsync
public class ApplicationListenerDemo {

    public static void main(String[] args) {
        // GenericApplicationContext context = new GenericApplicationContext();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("接收到 Spring 事件: " + event);
            }
        });
        // 将引导类 ApplicationListenerDemo 作为 Configuration Class
        context.register(ApplicationListenerDemo.class);
        context.register(MyApplicationListener.class);
        // 启动 Spring 应用上下文
        context.refresh();
        // 启动 Spring 上下文
        context.start();
        // 关闭 Spring 应用上下文
        context.close();
    }

    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            println("MyApplicationListener - 接收到 Spring 事件: " + event);
        }

    }

    @Async
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        println("接收到 Spring ContextRefreshedEvent 事件");
    }

    @EventListener
    public void onApplicationEvent(ContextStartedEvent event) {
        println("接收到 Spring ContextStartedEvent 事件");
    }

    @EventListener
    public void onApplicationEvent(ContextClosedEvent event) {
        println("接收到 Spring ContextClosedEvent 事件");
    }

    private static void println(Object printable) {
        System.out.printf("[线程：%s] : %s\n", Thread.currentThread().getName(), printable);
    }

}
