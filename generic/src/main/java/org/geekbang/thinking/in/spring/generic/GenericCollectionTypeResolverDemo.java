package org.geekbang.thinking.in.spring.generic;

import org.springframework.core.GenericCollectionTypeResolver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link org.springframework.core.GenericCollectionTypeResolver} 示例
 */
public class GenericCollectionTypeResolverDemo {

    private static StringList stringList;

    private static List<String> strings;

    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println(GenericCollectionTypeResolver.getCollectionType(StringList.class));
        System.out.println(GenericCollectionTypeResolver.getCollectionType(ArrayList.class));

        Field stringListField = GenericCollectionTypeResolverDemo.class.getDeclaredField("stringList");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(stringListField));

        Field stringsField = GenericCollectionTypeResolverDemo.class.getDeclaredField("strings");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(stringsField));
    }

}
