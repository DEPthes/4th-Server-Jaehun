package depth.httpservlet.servlet;

import java.io.IOException;

public class Servlet implements MyServlet {
    @Override
    public void service(HttpRequest req, HttpResponse res) throws IOException {
        String name = req.queryParams.getOrDefault("name", "Guest");
        String body = "<html><body><h1>Hello, " + name + "!<h1></body></html>";
        res.ok(body);
    }
}
