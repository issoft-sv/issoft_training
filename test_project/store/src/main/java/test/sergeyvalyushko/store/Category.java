package test.sergeyvalyushko.store;

import test.sergeyvalyushko.store.helpers.RandomStorePopulator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Category {
    protected List<Product> productList = new ArrayList<Product>();
    public abstract List<Product> addProduct(RandomStorePopulator populator);
    public abstract String getName();
    public abstract List<Product> getProductList();
}
