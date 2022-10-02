package org.geekbang.thinking.in.spring.generic;

import org.springframework.core.ResolvableType;

/**
 * {@link ResolvableType} 示例
 */
public class ResolvableTypeDemo {

    public static void main(String[] args) {
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);

        System.out.println(resolvableType.getSuperType());
        System.out.println(resolvableType.getSuperType().getSuperType());

        System.out.println(resolvableType.asCollection().resolve());
        System.out.println(resolvableType.asCollection().resolveGeneric(0));

    }
}
