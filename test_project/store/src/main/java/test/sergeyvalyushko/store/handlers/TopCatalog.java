package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
import test.sergeyvalyushko.store.Product;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class TopCatalog extends SortCatalog {

    public TopCatalog(AbstractHandler nextHandler) {
        super(nextHandler);
        sortParameters.add("top");
    }

    @Override
    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException {
        super.handleRequest(input, catalog);
    }

    @Override
    protected List<Product> prepareCatalog(List<Product> list, String param) throws IOException {
        return super.prepareCatalog(list, "price").subList(0,3);
    }
}
