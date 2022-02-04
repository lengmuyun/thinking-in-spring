package sun.net.www.protocol.x;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * X Handler 测试示例
 *
 * @author fangkuangzhang
 * @date 2022/2/4 17:25
 */
public class HandlerTest {

    public static void main(String[] args) throws IOException {
        URL url = new URL("x:///META-INF/default.properties"); // 类似于 classpath:/META-INF/default.properties
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));
    }

}
