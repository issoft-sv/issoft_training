package test.sergeyvalyushko.store;

import test.sergeyvalyushko.common.Reflection;
import test.sergeyvalyushko.store.helpers.RandomStorePopulator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Catalog {

    private List<Category> categories;
    private static Catalog instance;
    private CopyOnWriteArrayList<Product> purchasedGoods = new CopyOnWriteArrayList<>();

    public CopyOnWriteArrayList<Product> getPurchasedGoods() {
        return purchasedGoods;
    }

    private Catalog() {
        String location = "test.sergeyvalyushko.store";
        Reflection<Category> reflection = new Reflection<Category>();
        categories = reflection.createClassesInstances(Category.class, location);
        for (Category category : categories) {
            category.addProduct(new RandomStorePopulator());
        }
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

