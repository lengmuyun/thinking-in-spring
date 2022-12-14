package org.geekbang.thinking.in.spring.questions;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * BeanFactory 循环引用（依赖）示例
 */
public class CircularReferencesDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 Configuration Class
        context.register(CircularReferencesDemo.class);

        // 如果设置为 false，则抛出异常信息: Requested bean is currently in creation: Is there an unresolvable circular reference?
        // 默认值为 true
        context.setAllowCircularReferences(true);

        // 启动 Spring 应用上下文
        context.refresh();

        System.out.println("Student : " + context.getBean(Student.class));
        System.out.println("ClassRoom : " + context.getBean(ClassRoom.class));

        // 关闭 Spring 应用上下文
        context.close();
    }

    @Bean
    public static ClassRoom classRoom() {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setName("class[101]");
        return classRoom;
    }

    @Bean
    public static Student student() {
        Student student = new Student();
        student.setId(1L);
        student.setName("张三");
        return student;
    }

}
