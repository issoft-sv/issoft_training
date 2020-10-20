package test.sergeyvalyushko.store;

import test.sergeyvalyushko.store.helpers.RandomStorePopulator;
import test.sergeyvalyushko.store.http.HttpClient;

import java.util.ArrayList;
import java.util.List;

public class CatalogPopulator {
    public List<Category> populateCatalog (Populator populator){
        List<Category> categories = new ArrayList<>();
        switch (populator){
            case DB:
                DBhandler dbh = new DBhandler();
                categories = dbh.createCatalog();
                break;
            case FAKER:
                RandomStorePopulator rsp = new RandomStorePopulator();
                categories = rsp.populateCatalog();
                break;
            case HTTP_SERVER:
                HttpClient client = new HttpClient();
                categories = client.getCatalog();
                break;
        } return categories;
    }
}
