package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;

/**
 * {@link DestructionAwareBeanPostProcessor} 实现
 *
 * @author fangkuangzhang
 * @date 2022/1/10 21:55
 */
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if ("userHolder".equals(beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            // afterSingletonsInstantiated() = The user holder V8
            // UserHolder description = "The user holder V8"
            userHolder.setDescription("The user holder V9");
            System.out.println("postProcessBeforeDestruction() = " + userHolder.getDescription());
        }
    }

}
