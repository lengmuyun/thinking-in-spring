package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * User Holder 类
 *
 * @author fangkuangzhang
 * @date 2022/1/4 16:39
 */
public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware,
        InitializingBean, SmartInitializingSingleton, DisposableBean {

    private User user;

    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private String beanName;

    private Integer number;

    private String description;

    private Environment environment;

    public UserHolder(User user) {
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", beanName='" + beanName + '\'' +
                ", number=" + number +
                ", description='" + description + '\'' +
                '}';
    }

    @PostConstruct
    public void initPostConstruct() {
        // postProcessBeforeInitialization V3 -> PostConstruct V4
        this.description = "The user holder V4";
        System.out.println("initPostConstruct() = " + this.description);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // PostConstruct V4 -> afterPropertiesSet V5
        this.description = "The user holder V5";
        System.out.println("afterPropertiesSet() = " + this.description);
    }

    public void init() {
        // afterPropertiesSet V5 -> init-method V5
        this.description = "The user holder V6";
        System.out.println("init() = " + this.description);
    }

    @Override
    public void afterSingletonsInstantiated() {
        // postProcessAfterInitialization V7 -> afterSingletonsInstantiated V8
        this.description = "The user holder V8";
        System.out.println("afterSingletonsInstantiated() = " + this.description);
    }

    @PreDestroy
    public void preDestory() {
        // postProcessBeforeDestruction V9 -> @PreDestroy V10
        this.description = "The user holder V10";
        System.out.println("preDestory() = " + this.description);
    }

    @Override
    public void destroy() throws Exception {
        // @PreDestroy V10 -> destory V11
        this.description = "The user holder V11";
        System.out.println("destroy() = " + this.description);
    }

    public void doDestroy() {
        // destory V11 -> destory-method V12
        this.description = "The user holder V12";
        System.out.println("doDestroy() = " + this.description);
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("The UserHolder is finalized...");
    }

}
