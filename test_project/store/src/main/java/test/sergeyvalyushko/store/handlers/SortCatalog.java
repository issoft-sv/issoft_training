package test.sergeyvalyushko.store.handlers;

import test.sergeyvalyushko.store.Product;
import test.sergeyvalyushko.store.helpers.XmlParser;

import java.io.IOException;
import java.util.*;

public class SortCatalog extends DisplayCatalog {

    public SortCatalog(AbstractHandler nextHandler) {
        super(nextHandler);
        handledInput = Arrays.asList("name", "price");
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
