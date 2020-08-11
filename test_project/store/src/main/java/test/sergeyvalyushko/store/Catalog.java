package test.sergeyvalyushko.store;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.common.Reflection;
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

    public RandomStorePopulator getPopulator() {
        return populator;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public static Catalog getInstance() {
        if (instance == null) {
            instance = new Catalog();
        }
        return instance;
    }

    public List<Product> sortCatalog(List<Product> prodList, String param) throws ParserConfigurationException, SAXException, IOException {
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

