package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
import test.sergeyvalyushko.store.Product;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SelectProduct extends SelectCategory {

    public SelectProduct(AbstractHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException {
        if (categorySelected == true) {
            try {
                int productIndex = Integer.parseInt(input);
                List<Product> currentProductList = catalog.getCategories().get(categoryIndex).getProductList();
                if (productIndex >= 0 && productIndex < currentProductList.size()){
                    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
                    Runnable cleaner = () -> {
                        catalog.getPurchasedGoods().clear();
                    };
                    scheduler.scheduleAtFixedRate(cleaner, 2, 2, TimeUnit.MINUTES);
                    int a = 1;
                    int b = 29;
                    int processTime = a + (int) (Math.random() * b);
                    Runnable task = () -> {
                        catalog.getPurchasedGoods().add(currentProductList.get(productIndex));
                    };
                    scheduler.schedule(task, processTime, TimeUnit.MINUTES);
                    categorySelected = false;
                    System.out.println(currentProductList.get(productIndex).getName());
                } else System.out.println("Enter please correct product number");
            } catch (NumberFormatException e) {super.handleRequest(input, catalog);}
        } else super.handleRequest(input, catalog);
    }
}
