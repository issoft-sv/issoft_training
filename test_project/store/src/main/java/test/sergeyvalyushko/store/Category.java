package test.sergeyvalyushko.store;

import test.sergeyvalyushko.store.helpers.RandomStorePopulator;

import java.util.ArrayList;
import java.util.List;

public abstract class Category {
    protected String name;
    protected List<Product> productList = new ArrayList<Product>();

    public List<Product> addProduct(RandomStorePopulator populator) {
        populator.createData(name, productList);
        return productList;
    }

    public String getName(){
        return name;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
