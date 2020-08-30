package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
import test.sergeyvalyushko.store.Product;
import test.sergeyvalyushko.store.helpers.XmlParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortCatalog extends AbstractHandler {
    protected List<String> sortParameters = new ArrayList<>();

    public SortCatalog(AbstractHandler nextHandler) {
        super(nextHandler);
        sortParameters.add("name");
        sortParameters.add("price");
    }


    @Override
    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException {
        if (sortParameters.contains(input))displayCatalog(input, catalog);
        else super.handleRequest(input, catalog);
    }

    @Override
    protected List<Product> prepareCatalog(List<Product> list, String param) throws IOException {
        List<Product> listOutput = new ArrayList<>(list);
        listOutput = sortCatalog(listOutput, param);
        return listOutput;
    }

    protected List<Product> sortCatalog(List<Product> prodList, String param) throws IOException {
        XmlParser parser = new XmlParser();
        String a = parser.parseFromXml(param);
        if (a.equals("asc")) {
            if (param.equals("name")) {
                Collections.sort(prodList, Comparator.comparing(obj -> obj.getName()));
            } else if (param.equals("price")) {
                Collections.sort(prodList, Comparator.comparing(obj -> obj.getPrice()));
            } else System.out.println("Not implemented");
        } else if (a.equals("desc")) {
            if (param.equals("name")) {
                Collections.sort(prodList, (o1, o2) -> o2.getName().compareTo(o1.getName()));
            } else if (param.equals("price")) {
                Collections.sort(prodList, (o1, o2) -> o2.getPrice().compareTo(o1.getPrice()));
            } else System.out.println("Not implemented");
        } else System.out.println("Unknown parameter");
        return prodList;
    }
}
