package depth.httpservlet.servlet;

import java.io.IOException;
import java.io.OutputStream;

public class HttpResponse {
    private final OutputStream out;

    public HttpResponse(OutputStream out) {
        this.out = out;
    }

    // response 전달
    public void send(String body, String status) throws IOException {
        String response = "HTTP/1.1 " + status + "\r\n" +
                "Content-Type: text/html; charset=UTF-8\r\n" +
                "Content-Length: " + body.getBytes().length + "\r\n" +
                "\r\n" +
                body;
        out.write(response.getBytes());
        out.flush();
    }

    // 200 OK(성공)
    public void ok(String body) throws IOException {
        send(body, "200 OK");
    }

    // 404 Not Found(실패)
    public void notFound() throws IOException {
        String body = "<html><body><h1>404 Not Found</h1></body></html>";
        send(body, "404 Not Found");
    }
}
