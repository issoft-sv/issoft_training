package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
import test.sergeyvalyushko.store.helpers.RandomStorePopulator;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TopCatalog extends AbstractHandler {
    public TopCatalog(AbstractHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException {
        if (input.equals("top")){
            RandomStorePopulator.createCatalog(input, catalog);
        }
        else super.handleRequest(input, catalog);
    }
}
