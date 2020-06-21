package test.sergeyvalyushko.store;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.common.ReflectionUser;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Catalog {

    String location = "test.sergeyvalyushko.store";
    ReflectionUser<Category> reflection = new ReflectionUser<Category>();

    protected void displayCatalog() {
        for (Category category : reflection.createClassesInstances(Category.class, location)) {
            System.out.println(category.getName() + ":");
            for (Product prod : category.addProduct()) {
                System.out.println(prod.getName() + " - price: " + prod.getPrice() + ", made_date: " + prod.getDate());
            }
        }
    }

    protected void displaySortedCatalog(String param) throws ParserConfigurationException, SAXException, IOException {
        for (Category category : reflection.createClassesInstances(Category.class, location)) {
            System.out.println(category.getName() + ":");
            for (Product prod : this.sortCatalog(category.addProduct(), param)) {
                System.out.println(prod.getName() + " - price: " + prod.getPrice() + ", made_date: " + prod.getDate());
            }
        }
    }

    protected void displayTop() throws ParserConfigurationException, SAXException, IOException {
        for (Category category : reflection.createClassesInstances(Category.class, location)) {
            System.out.println(category.getName() + ":");
            List<Product> list = this.sortCatalog(category.addProduct(), "price");
            for (int i = 0; i < 3; i++) {
                System.out.println(list.get(i).getName() + " - price: " + list.get(i).getPrice() + ", made_date: " + list.get(i).getDate());
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

