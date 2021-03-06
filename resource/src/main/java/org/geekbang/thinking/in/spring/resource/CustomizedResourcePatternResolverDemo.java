package org.geekbang.thinking.in.spring.resource;

import org.geekbang.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 自定义 {@link ResourcePatternResolver} 示例
 *
 * @author fangkuangzhang
 * @date 2022/2/4 15:10
 * @see org.springframework.core.io.support.PathMatchingResourcePatternResolver
 * @see org.springframework.util.PathMatcher
 */
public class CustomizedResourcePatternResolverDemo {

    public static void main(String[] args) throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());
        resolver.setPathMatcher(new JavaPathMatcher());
        // 读取当前 package 对应的所有的 .java 文件
        // *.java
        String javaFilePath = System.getProperty("user.dir") + "/resource/src/main/java/org/geekbang/thinking/in/spring/resource/";
        String locationPattern = javaFilePath + "*.java";
        Resource[] resources = resolver.getResources(locationPattern);
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
    }

    static class JavaPathMatcher implements PathMatcher {

        @Override
        public boolean isPattern(String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean match(String pattern, String path) {
            return path.endsWith(".java");
        }

        @Override
        public boolean matchStart(String pattern, String path) {
            return false;
        }

        @Override
        public String extractPathWithinPattern(String pattern, String path) {
            return null;
        }

        @Override
        public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
            return null;
        }

        @Override
        public Comparator<String> getPatternComparator(String path) {
            return null;
        }

        @Override
        public String combine(String pattern1, String pattern2) {
            return null;
        }

    }

}
