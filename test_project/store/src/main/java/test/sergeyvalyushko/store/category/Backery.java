package test.sergeyvalyushko.store.category;

import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.Product;

import java.util.*;

public class Backery extends Category {
    private static final String name = "Backery";
    @Override
    public String getName(){
        return name;
    }

    @Override
    protected List<Product> addProduct() {
        productList.add(new Product("loaf", 5.1, new Date()));
        productList.add(new Product("bun", 0.5, new Date()));
        productList.add(new Product("roll", 1.4, new Date()));
        productList.add(new Product("cookie", 3.5, new Date()));
        productList.add(new Product("bread", 2.2, new Date()));
        /*Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });*/
        return productList;
    }
}
