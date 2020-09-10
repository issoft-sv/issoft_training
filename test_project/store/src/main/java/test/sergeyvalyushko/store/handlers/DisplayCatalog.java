package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.Product;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DisplayCatalog extends AbstractHandler {

    public DisplayCatalog(AbstractHandler nextHandler) {
        super(nextHandler);
        handledInput = Arrays.asList("show");
    }

    @Override
    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException{
        if (handledInput.contains(input)) displayCatalog(input, catalog);
        else super.handleRequest(input, catalog);
    }

    protected List<Product> prepareCatalog(List<Product> list, String param) throws IOException {
        return list;
    }

    protected void displayCatalog (String input, Catalog catalog) throws IOException {
        int catalogposition = 0;
        for (Category category : catalog.getCategories()) {
            System.out.println(catalogposition + ". " + category.getName() + ":");
            int productposition = 0;
            for (Product product : this.prepareCatalog(category.getProductList(), input)) {
                System.out.println("   " + productposition + ". " + product.getName() + " - price: " + product.getPrice() + ", made_date: " + product.getDate());
                productposition++;
            }
            catalogposition++;
        }
    }
}
