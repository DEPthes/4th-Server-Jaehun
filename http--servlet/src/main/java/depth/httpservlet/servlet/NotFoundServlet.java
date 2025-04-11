package depth.httpservlet.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class NotFoundServlet implements MyServlet{
    @Override
    public void service(InputStream input, OutputStream output) throws IOException {
        String body = "<html><body><h1>404 Not Found</h1></body></html>";
        String response = "HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: text/html; charset=UTF-8\r\n" +
                "Content-Length: " + body.getBytes().length + "\r\n" +
                "\r\n" +
                body;
        output.write(response.getBytes());
        output.flush();
    }
}
