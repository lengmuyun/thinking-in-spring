package org.geekbang.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.security.auth.Destroyable;

/**
 * 默认{@link UserFactory}实现
 *
 * @author fangkuangzhang
 * @date 2021/7/11 16:56
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct : UserFactory 初始化中...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet() : UserFactory 初始化中...");
    }

    public void doInit() {
        System.out.println("自定义初始化方法 doInit() : UserFactory 初始化中...");
    }

    @PreDestroy
    public void destoryed() {
        System.out.println("@PreDestroy : UserFactory 销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy() : UserFactory 销毁中...");
    }

    public void doDestory() {
        System.out.println("自定义销毁方法 doDestroy() : UserFactory 销毁中...");
    }

}
