package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
import test.sergeyvalyushko.store.Product;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class ExitApp extends AbstractHandler {
    public ExitApp(AbstractHandler nextHandler) {super(nextHandler);}

    @Override
    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException {
        if (input.equals("exit")){
            System.exit(0);
        }
        else super.handleRequest(input, catalog);
    }

    @Override
    List<Product> prepareCatalog(List<Product> list, String param) throws IOException {
        return null;
    }
}
