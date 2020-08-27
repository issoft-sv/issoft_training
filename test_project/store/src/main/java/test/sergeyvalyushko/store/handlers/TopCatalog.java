package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
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
        if (input.equals("top")) displayCatalog(input, catalog);
        else super.handleRequest(input, catalog);
    }

    @Override
    protected List<Product> prepareList(List<Product> list, String param) throws IOException {
        List<Product> listOutput = new ArrayList<>();
        listOutput.addAll(list);
        listOutput = sortCatalog(listOutput, "price").subList(0,3);
        return listOutput;
    }
}
