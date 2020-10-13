package test.sergeyvalyushko.store;

import test.sergeyvalyushko.store.helpers.RandomStorePopulator;
import test.sergeyvalyushko.store.http.HttpClient;

import java.util.ArrayList;
import java.util.List;

public class CatalogPopulator {
    public List<Category> populateCatalog (String populator){
        List<Category> categories = new ArrayList<>();
        switch (populator){
            case ("db"):
                DBhandler dbh = new DBhandler();
                categories = dbh.createCatalog();
                break;
            case ("faker"):
                RandomStorePopulator rsp = new RandomStorePopulator();
                categories = rsp.populateCatalog();
                break;
            case ("http_server"):
                HttpClient client = new HttpClient();
                categories = client.httpRequest();
                break;
        } return categories;
    }
}
