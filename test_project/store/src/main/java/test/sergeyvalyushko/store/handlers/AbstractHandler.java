package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.Product;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public abstract class AbstractHandler {
    protected AbstractHandler nextHandler;

    public AbstractHandler(AbstractHandler nextHandler){
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
            for (Product product : this.prepareCatalog(category.getProductList(), input)) {
                System.out.println(product.getName() + " - price: " + product.getPrice() + ", made_date: " + product.getDate());
            }
        }
    }

    abstract List<Product> prepareCatalog(List<Product> list, String param) throws IOException;
}
