package test.sergeyvalyushko.store.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.*;
import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.helpers.RandomStorePopulator;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class LocalHttpServer {
    private HttpServer server;
    public void startServer () {
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 10);
            createContext("/test1", new Handler());
            createContext("/test2", new Handler1());
            server.start();
        } catch (Exception e) {e.printStackTrace();}
    }
    static class Handler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            RandomStorePopulator rsp = new RandomStorePopulator();
            List<Category> categories = rsp.populateCatalog();
            ObjectMapper mapper = new ObjectMapper();
            byte[] jsonInBytes = mapper.writeValueAsBytes(categories);
            Headers headers = exchange.getResponseHeaders();
            headers.add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, jsonInBytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(jsonInBytes);
            os.close();
        }
    }
    static class Handler1 implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // put selected product in the cart
            String response = "Your product is added to the cart";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    private void createContext (String path, HttpHandler handler){
        server.createContext(path, handler).setAuthenticator(new BasicAuthenticator("test") {
            @Override
            public boolean checkCredentials(String user, String pwd) {
                return user.equals("test") && pwd.equals("test");
            }
        });
    }
}
