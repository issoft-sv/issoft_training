package test.sergeyvalyushko.store.category;

import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.Product;
import test.sergeyvalyushko.store.helpers.RandomStorePopulator;

import java.util.List;

public class Beer extends Category {
    private static final String name = "Beer";
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
