package test.sergeyvalyushko.store;

import test.sergeyvalyushko.store.helpers.RandomStorePopulator;

import java.util.List;

public interface Category {
    public List<Product> addProduct(RandomStorePopulator populator);
    public String getName();
    public List<Product> getProductList();
}
