package depth.httpservlet.servlet;

import java.io.*;
import java.util.*;

public class HttpRequest {
    public String method;
    public String path;
    public String queryString;
    public Map<String, String> queryParams = new HashMap<>();
    public Map<String, String> headers = new HashMap<>();

    public HttpRequest(String requestLine, BufferedReader reader) throws IOException {
        String[] parts = requestLine.split(" ");
        this.method = parts[0];
        String[] uriParts = parts[1].split("\\?");
        this.path = uriParts[0];

        if (uriParts.length > 1) {
            this.queryString = uriParts[1];
            for (String param : queryString.split("&")) {
                String[] kv = param.split("=");
                if (kv.length == 2) {
                    queryParams.put(kv[0], kv[1]);
                }
            }
        }

        // Header 파싱
        String line;
        while (!(line = reader.readLine()).isEmpty()) {
            String[] headerParts = line.split(": ");
            if (headerParts.length == 2) {
                headers.put(headerParts[0], headerParts[1]);
            }
        }
    }
}
