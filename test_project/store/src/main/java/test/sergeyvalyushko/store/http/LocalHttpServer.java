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
    public void startServer () {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 10);
            server.createContext("/test1", new Handler()).setAuthenticator(new BasicAuthenticator("test") {
                @Override
                public boolean checkCredentials(String user, String pwd) {
                    return user.equals("test") && pwd.equals("test");
                }
            });
            server.start();
        } catch (Exception e) {e.printStackTrace();}
    }

    static class Handler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            RandomStorePopulator rsp = new RandomStorePopulator();
            List<Category> categories = rsp.populateCatalog();
            ObjectMapper mapper = new ObjectMapper();
            byte[] jsonInString = mapper.writeValueAsBytes(categories);
            Headers headers = exchange.getResponseHeaders();
            headers.add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, jsonInString.length);
            OutputStream os = exchange.getResponseBody();
            os.write(jsonInString);
            os.close();
        }
    }
}
