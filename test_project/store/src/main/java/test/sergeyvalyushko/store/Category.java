package test.sergeyvalyushko.store;

import java.util.ArrayList;
import java.util.List;

public abstract class Category {
    protected String name;
    protected List<Product> productList = new ArrayList<Product>();

    public String getName(){
        return name;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
