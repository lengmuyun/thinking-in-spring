package org.geekbang.thinking.in.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 注入 {@link ResourceLoader} 对象示例
 *
 * @author fangkuangzhang
 * @date 2022/2/4 15:28
 * @see AnnotationConfigApplicationContext
 * @see Resource
 * @see ResourceLoader
 */
public class InjectingResourceLoaderDemo implements ResourceLoaderAware {

    @Autowired
    private ResourceLoader autowiredResourceLoader;

    private ResourceLoader resoureLoader;

    @Autowired
    private AbstractApplicationContext applicationContext;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resoureLoader = resourceLoader;
    }

    @PostConstruct
    public void init() {
        System.out.println("resoureLoader == autowiredResourceLoader : " + (resoureLoader == autowiredResourceLoader));
        System.out.println("resoureLoader == applicationContext : " + (resoureLoader == applicationContext));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectingResourceLoaderDemo.class);
        context.refresh();
        context.close();
    }

}
