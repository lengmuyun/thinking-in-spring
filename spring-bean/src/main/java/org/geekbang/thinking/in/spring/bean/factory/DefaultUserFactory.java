package org.geekbang.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * 默认{@link UserFactory}实现
 *
 * @author fangkuangzhang
 * @date 2021/7/11 16:56
 */
public class DefaultUserFactory implements UserFactory, InitializingBean {

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct 初始化...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet() 初始化...");
    }

    public void initUserFactory() {
        System.out.println("initUserFactory 初始化...");
    }

}
