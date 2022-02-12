package org.geekbang.thinking.in.spring.validation;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * JavaBeans 示例
 *
 * @author fangkuangzhang
 * @date 2022/2/12 17:41
 */
public class JavaBeansDemo {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(ValidationUser.class, Object.class);
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(pd -> {
                    Method readMethod = pd.getReadMethod();
                    Method writeMethod = pd.getWriteMethod();
                    System.out.println("readMethod: " + readMethod + ", writeMethod: " + writeMethod);
                });

        Stream.of(beanInfo.getMethodDescriptors()).forEach(System.out::println);
    }

    static class ValidationUser {
        private Integer _id;
        private String name;

        public Integer getId() {
            return _id;
        }

        public void setId(Integer _id) {
            this._id = _id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
