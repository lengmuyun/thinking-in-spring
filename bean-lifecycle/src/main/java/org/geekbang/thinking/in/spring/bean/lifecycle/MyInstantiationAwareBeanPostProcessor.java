package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * BeanPostProcessor
 *
 * @author fangkuangzhang
 * @date 2022/1/8 20:35
 */
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if ("superUser".equals(beanName) && SuperUser.class.equals(beanClass)) {
            // 把配置完成 supreUser Bean 覆盖
            return new SuperUser();
        }
        return null; // 保持 Spring IoC 容器的实例化操作
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("user".equals(beanName) && User.class.equals(bean.getClass())) {
            User user = (User) bean;
            user.setId(2L);
            user.setName("fkz");
            // user 对象不允许属性赋值(填入) (配置元信息 -> 属性值)
            return false;
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if ("userHolder".equals(beanName) && UserHolder.class.equals(bean.getClass())) {
            MutablePropertyValues mpvs = (pvs instanceof MutablePropertyValues) ? (MutablePropertyValues) pvs : new MutablePropertyValues();
            mpvs.add("number", "1");
            if (mpvs.contains("description")) {
                mpvs.removePropertyValue("description");
            }
            mpvs.add("description", "The user holder V2");
            return mpvs;
        }
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userHolder".equals(beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            userHolder.setDescription("The user holder V3");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("userHolder".equals(beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            userHolder.setDescription("The user holder V7");
        }
        return bean;
    }
}
