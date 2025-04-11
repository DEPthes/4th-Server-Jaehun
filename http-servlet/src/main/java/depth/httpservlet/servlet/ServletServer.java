package depth.httpservlet.servlet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServletServer {
    public static void main(String args[]) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Running on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream output = clientSocket.getOutputStream()
            ){
                String requestLine = reader.readLine();
                if (requestLine == null || requestLine.isEmpty()) {
                    clientSocket.close();
                    continue;
                }

                HttpRequest req = new HttpRequest(requestLine, reader);
                HttpResponse res = new HttpResponse(output);

                MyServlet servlet;
                if(req.path.equals("/hello")) {
                    servlet = new Servlet();
                } else {
                    servlet = new NotFoundServlet();    // 404 Not Found
                }

                servlet.service(req, res);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                clientSocket.close();
            }
        }
    }
}
