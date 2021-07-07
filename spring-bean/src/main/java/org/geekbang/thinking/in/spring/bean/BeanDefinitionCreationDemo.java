package org.geekbang.thinking.in.spring.bean;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建示例
 * @author fangkuangzhang
 * @date 2021/7/7 22:42
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
        // 使用BeanDefinitionBuilder构建BeanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 1L);
        beanDefinitionBuilder.addPropertyValue("name", "fkz");
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // 通过AbstractBeanDefinition子类构建BeanDefinition
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("id", 1L).add("name", "fkz");
        genericBeanDefinition.setPropertyValues(propertyValues);
    }

}
