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
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();
            ){
                // Request Line만 읽음
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String requestLine = reader.readLine();
                System.out.println("Request: " + requestLine);

                // 간단한 경우 routing
                MyServlet servlet;
                if(requestLine.contains("/hello")) {
                    servlet = new Servlet();
                } else {
                    servlet = new NotFoundServlet();    // 404 Not Found
                }

                // request 처리
                servlet.service(inputStream, outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                clientSocket.close();
            }
        }
    }
}
