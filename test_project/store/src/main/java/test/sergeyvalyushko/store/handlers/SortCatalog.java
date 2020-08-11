package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.Product;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SortCatalog extends AbstractHandler {
    public SortCatalog(AbstractHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException {
        if (input.equals("name") || input.equals("price")){
            for (Category category : catalog.getCategories()) {
                List<Product> productListSorted = new ArrayList<>();
                System.out.println(category.getName() + ":");
                productListSorted.addAll(category.getProductList());
                for (Product prod : catalog.sortCatalog(productListSorted, input)) {
                    System.out.println(prod.getName() + " - price: " + prod.getPrice() + ", made_date: " + prod.getDate());
                }
            }

        }
        else super.handleRequest(input, catalog);
    }
}
