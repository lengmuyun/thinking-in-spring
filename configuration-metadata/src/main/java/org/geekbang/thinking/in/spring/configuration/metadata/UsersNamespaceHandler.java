package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * "users" {@link org.springframework.beans.factory.xml.NamespaceHandler} 实现
 *
 * @author fangkuangzhang
 * @date 2022/2/1 15:18
 */
public class UsersNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        // 将 "user" 元素注册对应的 BeanDefinitionParser 实现
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }

    public static class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
        @Override
        protected Class<?> getBeanClass(Element element) {
            return User.class;
        }

        @Override
        protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
            setPropertyValue("id", element, builder);
            setPropertyValue("name", element, builder);
            setPropertyValue("city", element, builder);
        }

        public void setPropertyValue(String attrName, Element element, BeanDefinitionBuilder builder) {
            String attrValue = element.getAttribute(attrName);
            if (StringUtils.hasText(attrValue)) {
                builder.addPropertyValue(attrName, attrValue);
            }
        }

    }

}
