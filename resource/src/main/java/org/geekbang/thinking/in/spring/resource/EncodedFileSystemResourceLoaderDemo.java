package org.geekbang.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 * 带有字符编码的 {@link FileSystemResourceLoader} 示例
 *
 * @author fangkuangzhang
 * @date 2022/2/4 13:02
 * @see FileSystemResource
 * @see EncodedResource
 * @see FileSystemResourceLoader
 */
public class EncodedFileSystemResourceLoaderDemo {

    public static void main(String[] args) throws IOException {
        String javaFilePath = System.getProperty("user.dir") + "/resource/src/main/java/org/geekbang/thinking/in/spring/resource/EncodedFileSystemResourceLoaderDemo.java";
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource(javaFilePath);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        try (Reader reader = encodedResource.getReader()) {
            System.out.println(IOUtils.toString(reader));
        }
    }

}
