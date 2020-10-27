package test.sergeyvalyushko.store.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import test.sergeyvalyushko.store.Category;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.List;

public class HttpClient {
    private HttpURLConnection connection;

    public List<Category> getCatalog() {
        getConnection("/test1", "GET");
        String contentType = connection.getHeaderField("Content-type");
        if (contentType.equals("application/json")) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.readValue(connection.getInputStream(), new TypeReference<List<Category>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else System.out.println("Unexpected content-type");
        connection.disconnect();
        return null;
    }
    public void putInCart(List<Category> categories){
        getConnection("/test2", "POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setDoOutput(true);
        try(OutputStream os = connection.getOutputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            byte[] postData = mapper.writeValueAsBytes(categories.get(1).getProductList().get(1)); // just for example
            os.write(postData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        } catch (IOException e){e.printStackTrace();}
        connection.disconnect();
    }
    private void getConnection (String file, String method) {
        try {
            URL address = new URL("http", "localhost", 8000, file);
            String encoding = Base64.getEncoder().encodeToString(("test:test").getBytes("UTF-8"));
            connection = (HttpURLConnection) address.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
