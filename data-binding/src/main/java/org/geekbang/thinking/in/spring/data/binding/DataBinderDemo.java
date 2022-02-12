package org.geekbang.thinking.in.spring.data.binding;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link DataBinder} 示例
 *
 * @author fangkuangzhang
 * @date 2022/2/12 10:34
 */
public class DataBinderDemo {

    public static void main(String[] args) {
        BindingUser user = new BindingUser();

        DataBinder binder = new DataBinder(user, "user");

        // 创建 PropertyValues
        Map<String, Object> source = new HashMap<>();
        source.put("id", 1L);
        source.put("name", "lmy");

        // a. PropertyValues 存在 BindingUser 中不存在的属性
        // DataBinder 特性一: 忽略未知的属性
        source.put("age", 18);

        // b. PropertyValues 存在一个嵌套属性，比如 company.name
        // DataBinder 特性二: 支持嵌套属性
//        source.put("company", new Company());
        source.put("company.name", "hm");

        MutablePropertyValues pvs = new MutablePropertyValues(source);
        // 1. 调整 IgnoreUnknownFields true（默认） -> false（抛出异常，age 字段不存在于 BindingUser 类）
        // binder.setIgnoreUnknownFields(false);

        // 2. 调整自动增加嵌套路径 true（默认） —> false
        binder.setAutoGrowNestedPaths(false);

        // 3. 调整 ignoreInvalidFields false(默认） -> true（默认情况调整不变化，需要调增 AutoGrowNestedPaths 为 false）
        binder.setIgnoreInvalidFields(true);

        binder.bind(pvs);
        System.out.println(user);

        // 4. 获取绑定结果（结果包含错误文案 code，不会抛出异常）
        BindingResult bindingResult = binder.getBindingResult();
        System.out.println(bindingResult);
    }

    static class BindingUser {

        private Integer id;

        private String name;

        private Company company;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Company getCompany() {
            return company;
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        @Override
        public String toString() {
            return "BindingUser{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", company=" + company +
                    '}';
        }

    }

    static class Company {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Company{" +
                    "name='" + name + '\'' +
                    '}';
        }

    }

}
