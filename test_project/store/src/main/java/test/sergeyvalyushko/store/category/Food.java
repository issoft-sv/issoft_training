package test.sergeyvalyushko.store.category;

import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.Product;
import test.sergeyvalyushko.store.RandomStorePopulator;

import java.util.List;

public class Food extends Category {
    private static final String name = "Food";
    @Override
    public String getName(){
        return name;
    }

    @Override
    protected List<Product> addProduct(RandomStorePopulator populator) {
        for (int i = 0; i < 5; i++) populator.createData(name, productList);
        productListSorted.addAll(productList);
        return productList;
    }
}
