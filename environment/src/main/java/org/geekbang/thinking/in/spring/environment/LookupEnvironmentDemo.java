package org.geekbang.thinking.in.spring.environment;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import static org.springframework.context.ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME;

/**
 * 依赖查找 {@link org.springframework.core.env.Environment} 示例
 */
@Configuration
public class LookupEnvironmentDemo implements EnvironmentAware {

    private Environment environment;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(LookupEnvironmentDemo.class);

        // 启动 Spring 应用上下文
        context.refresh();

        LookupEnvironmentDemo lookupEnvironmentDemo = context.getBean(LookupEnvironmentDemo.class);

        Environment environment = context.getBean(ENVIRONMENT_BEAN_NAME, Environment.class);
        System.out.println(lookupEnvironmentDemo.environment == environment);

        // 关闭 Spring 应用上下文
        context.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
