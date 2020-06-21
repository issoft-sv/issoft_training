package test.sergeyvalyushko.store.category;

import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vegetables extends Category {
    private static final String name = "Vegetables";
    @Override
    public String getName(){
        return name;
    }

    @Override
    protected List<Product> addProduct() {
        productList.add(new Product("potato", 0.8, new Date()));
        productList.add(new Product("garlic", 6.1, new Date()));
        productList.add(new Product("cucumber", 2.3, new Date()));
        productList.add(new Product("tomato", 4.0, new Date()));
        productList.add(new Product("onion", 1.1, new Date()));
        return productList;
    }
}
