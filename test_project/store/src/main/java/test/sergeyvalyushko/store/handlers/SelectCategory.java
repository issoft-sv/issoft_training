package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;

public class SelectCategory extends AbstractHandler {
    protected static int categoryIndex;
    protected static boolean categorySelected = false;

    public SelectCategory(AbstractHandler nextHandler) {
        super(nextHandler);
        handledInput = Arrays.asList("0", "1", "2");
    }

    @Override
    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException{
        if (handledInput.contains(input)) {
            categoryIndex = Integer.parseInt(input);
            categorySelected = true;
            System.out.println("Select product from category: " + catalog.getCategories().get(categoryIndex).getName());
        } else super.handleRequest(input, catalog);
    }
}
