package org.geekbang.thinking.in.spring.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Java 泛型示例
 *
 * @author fangkuangzhang
 */
public class GenericDemo {

    public static void main(String[] args) {
        Collection<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        // 编译时错误
        // list.add(1);

        // 泛型擦写
        Collection temp = list;
        // 编译通过
        temp.add(1);
        System.out.println(temp);
    }

}
