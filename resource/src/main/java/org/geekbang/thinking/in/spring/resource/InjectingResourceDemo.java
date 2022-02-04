package org.geekbang.thinking.in.spring.resource;

import org.geekbang.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Stream;

/**
 * 注入 {@link Resource} 对象示例
 *
 * @author fangkuangzhang
 * @date 2022/2/4 15:28
 * @see AnnotationConfigApplicationContext
 * @see Resource
 * @see Value
 */
public class InjectingResourceDemo {

    @Value("classpath:/META-INF/default.properties")
    private Resource resource;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] resources;

    // 此处只注入了一个
    @Value("classpath*:/META-INF/*.properties")
    private List<Resource> resourceList;

    @Value("${user.dir}")
    private String currentProjectPath;

    @PostConstruct
    public void init() {
        System.out.println(ResourceUtils.getContent(resource));
        System.out.println(currentProjectPath);
        System.out.println("===================print array resource start===================");
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
        System.out.println("===================print array resource end===================");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingResourceDemo.class);
        context.refresh();
        context.close();
    }

}
