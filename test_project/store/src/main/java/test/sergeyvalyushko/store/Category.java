package test.sergeyvalyushko.store;

import java.util.ArrayList;
import java.util.List;

public abstract class Category {
    protected List<Product> productList = new ArrayList<Product>();
    protected abstract List<Product> addProduct();
    public abstract String getName();
}
