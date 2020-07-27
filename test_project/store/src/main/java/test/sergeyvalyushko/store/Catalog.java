package test.sergeyvalyushko.store;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.helpers.Reflection;
import test.sergeyvalyushko.store.helpers.RandomStorePopulator;
import test.sergeyvalyushko.store.helpers.SaxExample;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Catalog {

    private RandomStorePopulator populator;
    private List<Category> categories;
    private static Catalog instance;

    private Catalog() {
        String location = "test.sergeyvalyushko.store";
        Reflection<Category> reflection = new Reflection<Category>();
        populator = new RandomStorePopulator();
        categories = reflection.createClassesInstances(Category.class, location);
    }

    public static Catalog getInstance() {
        if (instance == null) {
            instance = new Catalog();
        }
        return instance;
    }

    protected void displayCatalog(String pparam, String param) throws ParserConfigurationException, SAXException, IOException {
        List<Product> productListSorted = new ArrayList<>();
        for (Category category : categories) {
            System.out.println(category.getName() + ":");
            switch (pparam) {
                case ("init"): {
                    for (Product product : category.addProduct(populator)) {
                        System.out.println(product.getName() + " - price: " + product.getPrice() + ", made_date: " + product.getDate());
                    }
                    break;
                }
                case ("sorted"): {
                    productListSorted.addAll(category.getProductList());
                    for (Product product : this.sortCatalog(productListSorted, param)) {
                        System.out.println(product.getName() + " - price: " + product.getPrice() + ", made_date: " + product.getDate());
                    }
                    break;
                }
                case ("top"): {
                    productListSorted.addAll(category.getProductList());
                    List<Product> list = this.sortCatalog(productListSorted, "price");
                    for (int i = 0; i < 3; i++) {
                        System.out.println(list.get(i).getName() + " - price: " + list.get(i).getPrice() + ", made_date: " + list.get(i).getDate());
                    }
                    break;
                }
            }
        }
    }

    protected List<Product> sortCatalog(List<Product> prodList, String param) throws ParserConfigurationException, SAXException, IOException {
        SaxExample sax = new SaxExample();
        String a = sax.config().get(param);
        if (a.equals("asc")) {
            if (param.equals("name")) {
                Collections.sort(prodList, Comparator.comparing(obj -> obj.getName()));
            } else if (param.equals("price")) {
                Collections.sort(prodList, Comparator.comparing(obj -> obj.getPrice()));
            } else System.out.println("Not implemented");

        } else if (a.equals("desc")) {
            if (param.equals("name")) {
                Collections.sort(prodList, (o1, o2) -> o2.getName().compareTo(o1.getName()));
            } else if (param.equals("price")) {
                Collections.sort(prodList, (o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
            } else System.out.println("Not implemented");
        } else System.out.println("Unknown parameter");
        return prodList;
    }
}

