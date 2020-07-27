package test.sergeyvalyushko.store.category;

import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.Product;
import test.sergeyvalyushko.store.helpers.RandomStorePopulator;

import java.util.List;

public class FoodCategory implements Category {
    protected List<Product> productList;
    private static final String name = "Food";
    @Override
    public String getName(){
        return name;
    }

    @Override
    public List<Product> getProductList() { return productList; }

    @Override
    public List<Product> addProduct(RandomStorePopulator populator) {
        this.productList = populator.createData(name);
        return productList;
    }
}