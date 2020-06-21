package test.sergeyvalyushko.store.category;

import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dairy extends Category {
    private static final String name = "Dairy";
    @Override
    public String getName(){
        return name;
    }

    @Override
    protected List<Product> addProduct() {
        productList.add(new Product("cottage cheese", 6.5, new Date()));
        productList.add(new Product("yogurt", 2.8, new Date()));
        productList.add(new Product("cream", 3.5, new Date()));
        productList.add(new Product("milk", 2.5, new Date()));
        productList.add(new Product("cheese", 6.7, new Date()));
        return productList;
    }
}
