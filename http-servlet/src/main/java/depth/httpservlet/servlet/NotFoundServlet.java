package depth.httpservlet.servlet;

import java.io.IOException;

public class NotFoundServlet implements MyServlet{
    @Override
    public void service(HttpRequest req, HttpResponse res) throws IOException {
        res.notFound();
    }
}
