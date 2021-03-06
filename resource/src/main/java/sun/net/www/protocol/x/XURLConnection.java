package sun.net.www.protocol.x;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * X协议 {@link URLConnection} 实现
 *
 * @author fangkuangzhang
 * @date 2022/2/4 17:08
 */
public class XURLConnection extends URLConnection {

    private final ClassPathResource resource;

    /**
     * Constructs a URL connection to the specified URL. A connection to
     * the object referenced by the URL is not created.
     *
     * @param url the specified URL.
     */
    protected XURLConnection(URL url) {
        super(url);
        this.resource = new ClassPathResource(url.getPath());
    }

    @Override
    public void connect() throws IOException {
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return resource.getInputStream();
    }

}
