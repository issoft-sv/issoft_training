package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.Product;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DisplayCatalog extends AbstractHandler {
    public DisplayCatalog(AbstractHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException {
        if (input.equals("init")) {
            for (Category category : catalog.getCategories()) {
                if (category.getProductList().isEmpty()) {
                    System.out.println(category.getName() + ":");
                    for (Product prod : category.addProduct(catalog.getPopulator())) {
                        System.out.println(prod.getName() + " - price: " + prod.getPrice() + ", made_date: " + prod.getDate());
                    }
                } else {
                    System.out.println(category.getName() + ":");
                    for (Product prod : category.getProductList()) {
                        System.out.println(prod.getName() + " - price: " + prod.getPrice() + ", made_date: " + prod.getDate());
                    }
                }
            }
        } else super.handleRequest(input, catalog);
    }
}
