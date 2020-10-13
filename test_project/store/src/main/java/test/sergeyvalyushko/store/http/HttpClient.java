package test.sergeyvalyushko.store.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import test.sergeyvalyushko.store.Category;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class HttpClient {
    public List<Category> httpRequest(){
        try {
            URL address = new URL("http", "localhost", 8000, "/test1");
            String encoding = Base64.getEncoder().encodeToString(("test:test").getBytes("UTF-8"));
            HttpURLConnection connection = (HttpURLConnection) address.openConnection();
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            String contentType = connection.getHeaderField("Content-type");
            if (contentType.equals("application/json")) {
                try {
                    BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        sb.append(line).append(System.lineSeparator());
                    }
                    ObjectMapper mapper = new ObjectMapper();
                    String result = sb.toString();
                    return mapper.readValue(result, new TypeReference<List<Category>>() {});
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    connection.disconnect();
                }
            } else System.out.println("Unexpected content-type");
            } catch(IOException e){
                e.printStackTrace();
            }
        return null;
    }
}
