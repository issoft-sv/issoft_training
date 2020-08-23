package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
import test.sergeyvalyushko.store.helpers.RandomStorePopulator;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class SortCatalog extends AbstractHandler {
    public SortCatalog(AbstractHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException {
        if (input.equals("name") || input.equals("price")){
            RandomStorePopulator.createCatalog(input, catalog);
        }
        else super.handleRequest(input, catalog);
    }
}
