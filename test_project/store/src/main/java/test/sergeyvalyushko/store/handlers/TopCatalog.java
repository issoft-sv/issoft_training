package test.sergeyvalyushko.store.handlers;

import test.sergeyvalyushko.store.Product;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TopCatalog extends SortCatalog {

    public TopCatalog(AbstractHandler nextHandler) {
        super(nextHandler);
        handledInput = Arrays.asList("top");
    }

    @Override
    protected List<Product> prepareCatalog(List<Product> list, String param) throws IOException {
        return super.prepareCatalog(list, "price").subList(0,3);
    }
}
