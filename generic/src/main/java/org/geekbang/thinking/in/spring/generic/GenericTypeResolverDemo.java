package org.geekbang.thinking.in.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@link org.springframework.core.GenericTypeResolver} 示例
 */
public class GenericTypeResolverDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, Comparable.class, "getString");
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getStringList");
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getList");

        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        typeVariableMap.forEach((k,v) -> System.out.println("key=" + k + ",value=" + v));
    }

    private static void displayReturnTypeGenericInfo(Class<?> clazz, Class<?> genericIfc, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method = clazz.getMethod(methodName);
        Class<?> returnType = GenericTypeResolver.resolveReturnType(method, clazz);
        // 常规类作为方法返回值
        System.out.printf("GenericTypeResolver.resolveReturnType(%s,%s) = %s\n", methodName, clazz.getSimpleName(), returnType);

        Class<?> returnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, genericIfc);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s,%s) = %s\n", methodName, clazz.getSimpleName(), returnTypeArgument);
    }

    public static String getString() {
        return null;
    }

    public static StringList getStringList() {
        return null;
    }

    public static ArrayList<Object> getList() { // 泛型参数类型具体化
        return null;
    }

}
