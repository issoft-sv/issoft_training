package test.sergeyvalyushko.store;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.handlers.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Processor {
    protected void run(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException {
        AbstractHandler exitApp = new ExitApp(null);
        AbstractHandler topCatalog = new TopCatalog(exitApp);
        AbstractHandler sortCatalog = new SortCatalog(topCatalog);
        AbstractHandler displayCatalog = new DisplayCatalog(sortCatalog);
        AbstractHandler selectCategory = new SelectCategory(displayCatalog);
        AbstractHandler selectProduct = new SelectProduct(selectCategory);

        selectProduct.handleRequest(input, catalog);
    }
}
