package test.sergeyvalyushko.store;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Catalog {

    private List<Category> categories;
    private static Catalog instance;
    private List<Product> purchasedGoods = new CopyOnWriteArrayList<>();

    public List<Product> getPurchasedGoods() {
        return purchasedGoods;
    }

    private Catalog() {
        CatalogPopulator cp = new CatalogPopulator();
        categories = cp.populateCatalog(Populator.HTTP_SERVER);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public static Catalog getInstance() {
        if (instance == null) {
            instance = new Catalog();
        }
        return instance;
    }
}

