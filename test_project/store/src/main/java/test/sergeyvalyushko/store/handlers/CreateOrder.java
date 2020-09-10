package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
import test.sergeyvalyushko.store.Product;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class CreateOrder extends AbstractHandler {
    private static CreateOrder instance;
    private CopyOnWriteArrayList<Product> purchasedGoods = new CopyOnWriteArrayList<>();

    private CreateOrder(AbstractHandler nextHandler) {
        super(nextHandler);
        handledInput = Arrays.asList("0", "1", "2");
    }

    public static CreateOrder getInstance(AbstractHandler nextHandler) {
        if (instance == null) {
            instance = new CreateOrder(nextHandler);
        }
        return instance;
    }

    @Override
    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException{
        if (handledInput.contains(input)) {
            int categoryIndex = Integer.parseInt(input);
            System.out.println("Select product from category: " + catalog.getCategories().get(categoryIndex).getName());
            List<Product> currentProductList = catalog.getCategories().get(categoryIndex).getProductList();
            ExecutorService es = Executors.newCachedThreadPool();
            Runnable cleaner = () -> {
                while (1>0) {
                    try {
                        TimeUnit.MINUTES.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    purchasedGoods.clear();
                }
            };
            es.execute(cleaner);
            Scanner in = new Scanner(System.in); // require to select a product number
            if (in.hasNextInt()){
                int productIndex = in.nextInt();
                if (productIndex >= 0 && productIndex < currentProductList.size()){
                    int a = 1;
                    int b = 29;
                    int processTime = a + (int) (Math.random() * b);
                    Runnable task = () -> {
                        try {
                            TimeUnit.SECONDS.sleep(processTime);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                        purchasedGoods.add(currentProductList.get(productIndex));
                    };
                    es.execute(task);
                    System.out.println(currentProductList.get(productIndex).getName());
                } else System.out.println("Enter please correct category number then correct product number");
            } else System.out.println("Enter please correct category number then correct product number");
        } else super.handleRequest(input, catalog);
    }
}
