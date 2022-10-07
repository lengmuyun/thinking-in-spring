package org.geekbang.thinking.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.GenericApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.context.support.AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME;

/**
 * 异步事件处理器示例
 */
public class AsyncEventHandlerDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();

        // 1 添加自定义 Spring 事件监听器
        context.addApplicationListener(new MySpringEventListener());

        // 2 启动 Spring 应用上下文
        context.refresh(); // 初始化 ApplicationEventMulticaster

        // 依赖查找 ApplicationEventMulticaster
        ApplicationEventMulticaster multicaster = context.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME,
                ApplicationEventMulticaster.class);

        // 判断当前 ApplicationEventMulticaster 是否为 SimpleApplicationEventMulticaster
        if (multicaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) multicaster;
            // 切换 taskExecutor
            ExecutorService taskExecutor = Executors.newSingleThreadExecutor();
            // 同步 -> 异步
            simpleApplicationEventMulticaster.setTaskExecutor(taskExecutor);

            simpleApplicationEventMulticaster.setErrorHandler(t -> System.err.println("事件执行异常, msg: " + t.getMessage()));

            // 添加 ContextClosedEvent 事件处理
            multicaster.addApplicationListener((ApplicationListener<ContextClosedEvent>) event -> {
                if (!taskExecutor.isShutdown()) {
                    taskExecutor.shutdown();
                }
            });
        }

        context.addApplicationListener((ApplicationListener<MySpringEvent>) event -> {
            throw new RuntimeException("手动抛出异常");
        });

        // 3 发布自定义 Spring 事件
        context.publishEvent(new MySpringEvent("Hello,World"));

        // 4 关闭 Spring 应用上下文 (ContextClosedEvent)
        context.close();
    }

}
