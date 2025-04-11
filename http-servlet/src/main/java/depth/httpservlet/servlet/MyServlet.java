package depth.httpservlet.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface MyServlet {
    void service(HttpRequest req, HttpResponse res) throws IOException;
}
