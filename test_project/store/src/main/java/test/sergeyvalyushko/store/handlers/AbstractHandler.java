package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.Product;
import test.sergeyvalyushko.store.helpers.XmlParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AbstractHandler {
    protected AbstractHandler nextHandler;

    public AbstractHandler (AbstractHandler nextHandler){
        this.nextHandler = nextHandler;
    }

    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException {
        if (nextHandler != null) {
            nextHandler.handleRequest(input, catalog);
        } else System.out.println("Incorrect input");
    }

    protected void displayCatalog (String input, Catalog catalog) throws IOException {
        for (Category category : catalog.getCategories()) {
            System.out.println(category.getName() + ":");
            for (Product product : this.prepareList(category.getProductList(), input)) {
                System.out.println(product.getName() + " - price: " + product.getPrice() + ", made_date: " + product.getDate());
            }
        }
    }
    protected List<Product> prepareList (List<Product> list, String param) throws IOException {
        return null;
    }

    protected List<Product> sortCatalog(List<Product> prodList, String param) throws IOException {
        XmlParser parser = new XmlParser();
        String a = parser.parseFromXml(param);
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
