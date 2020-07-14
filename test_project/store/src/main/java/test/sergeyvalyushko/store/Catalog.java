package test.sergeyvalyushko.store;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.common.Reflection;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Catalog {

    String location = "test.sergeyvalyushko.store";
    Reflection<Category> reflection = new Reflection<Category>();
    RandomStorePopulator populator = new RandomStorePopulator();
    List<Category> instances = reflection.createClassesInstances(Category.class, location);

    protected void displayCatalog(String pparam, String param) throws ParserConfigurationException, SAXException, IOException {
        for (Category category : instances) {
            System.out.println(category.getName() + ":");
            switch (pparam) {
                case ("cat"): {
                    for (Product product : category.addProduct(populator)) {
                        System.out.println(product.getName() + " - price: " + product.getPrice() + ", made_date: " + product.getDate());
                    }
                    break;
                }
                case ("sorted"): {
                    for (Product product : this.sortCatalog(category.productListSorted, param)) {
                        System.out.println(product.getName() + " - price: " + product.getPrice() + ", made_date: " + product.getDate());
                    }
                    break;
                }
                case ("top"): {
                    List<Product> list = this.sortCatalog(category.productListSorted, "price");
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

