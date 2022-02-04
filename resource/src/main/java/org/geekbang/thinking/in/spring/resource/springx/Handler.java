package org.geekbang.thinking.in.spring.resource.springx;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * 简单地继承 {@link sun.net.www.protocol.x.Handler} 类
 *
 * @author fangkuangzhang
 * @date 2022/2/4 17:41
 */
public class Handler extends sun.net.www.protocol.x.Handler {

    public static void main(String[] args) throws IOException {
        URL url = new URL("springx:///META-INF/default.properties"); // 类似于 classpath:/META-INF/default.properties
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));
    }

}
