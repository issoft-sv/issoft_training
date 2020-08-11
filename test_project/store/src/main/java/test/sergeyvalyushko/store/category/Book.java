package test.sergeyvalyushko.store.category;

import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.Product;
import test.sergeyvalyushko.store.helpers.RandomStorePopulator;

import java.util.*;

public class Book extends Category {
    private static final String name = "Book";
    @Override
    public String getName(){
        return name;
    }

    @Override
    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public List<Product> addProduct(RandomStorePopulator populator) {
        for (int i = 0; i < 5; i++) populator.createData(name, productList);
        return productList;
    }
}
