package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
import test.sergeyvalyushko.store.Product;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class DisplayCatalog extends AbstractHandler {
    public DisplayCatalog(AbstractHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException {
        if (input.equals("show")) displayCatalog(input, catalog);
        else super.handleRequest(input, catalog);
    }

    @Override
    protected List<Product> prepareList(List<Product> list, String param) {
        List<Product> listOutput = list;
        return listOutput;
    }
}
