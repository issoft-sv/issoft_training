package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.Product;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TopCatalog extends AbstractHandler {
    public TopCatalog(AbstractHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException {
        List<Product> productListSorted = new ArrayList<>();
        if (input.equals("top")){
            for (Category category : catalog.getCategories()) {
                System.out.println(category.getName() + ":");
                productListSorted.addAll(category.getProductList());
                List<Product> list = catalog.sortCatalog(productListSorted, "price");
                for (int i = 0; i < 3; i++) {
                    System.out.println(list.get(i).getName() + " - price: " + list.get(i).getPrice() + ", made_date: " + list.get(i).getDate());
                }
            }

        }
        else super.handleRequest(input, catalog);
    }
}
