package sun.net.www.protocol.x;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * X 协议 {@link URLStreamHandler} 实现
 *
 * @author fangkuangzhang
 * @date 2022/2/4 17:07
 */
public class Handler extends URLStreamHandler {

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new XURLConnection(u);
    }

}
