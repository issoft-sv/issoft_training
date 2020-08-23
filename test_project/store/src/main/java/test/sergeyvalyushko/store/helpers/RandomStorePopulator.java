package test.sergeyvalyushko.store.helpers;

import com.github.javafaker.Faker;
import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;
import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.Product;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RandomStorePopulator {
    private Faker faker = new Faker();
    public List<Product> createData(String categoryName, List<Product> productList) {
        for (int i = 0; i < 5; i++) {
            switch (categoryName) {
                case ("Book"):
                    productList.add(new Product(faker.book().title(), (int) ((Math.random() * 10) + 1), faker.date().between(new Date(1212121212222L), new Date())));
                    break;
                case ("Beer"):
                    productList.add(new Product(faker.beer().name(), (int) ((Math.random() * 10) + 1), faker.date().between(new Date(1212121212222L), new Date())));
                    break;
                case ("Food"):
                    productList.add(new Product(faker.food().ingredient(), (int) ((Math.random() * 10) + 1), faker.date().between(new Date(1212121212222L), new Date())));
                    break;
            }
        }
        return productList;
    }
    public static void createCatalog (String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException {
        for (Category category : catalog.getCategories()) {
            List<Product> productListSorted = new ArrayList<>();
            System.out.println(category.getName() + ":");
            switch (input) {
                case ("init"): {
                    if (category.getProductList().isEmpty()) {
                        for (Product product : category.addProduct(catalog.getPopulator())) {
                            System.out.println(product.getName() + " - price: " + product.getPrice() + ", made_date: " + product.getDate());
                        }
                    } else {
                        for (Product product : category.getProductList()) {
                            System.out.println(product.getName() + " - price: " + product.getPrice() + ", made_date: " + product.getDate());
                        }
                    }
                    break;
                }
                case ("name"):
                case ("price"): {
//                    List<Product> productListSorted = new ArrayList<>();
                    productListSorted.addAll(category.getProductList());
                    for (Product prod : catalog.sortCatalog(productListSorted, input)) {
                        System.out.println(prod.getName() + " - price: " + prod.getPrice() + ", made_date: " + prod.getDate());
                    }
                    break;
                }
                case ("top"): {
//                    List<Product> productListSorted = new ArrayList<>();
                    productListSorted.addAll(category.getProductList());
                    List<Product> list = catalog.sortCatalog(productListSorted, "price");
                    for (int i = 0; i < 3; i++) {
                        System.out.println(list.get(i).getName() + " - price: " + list.get(i).getPrice() + ", made_date: " + list.get(i).getDate());
                    }
                    break;
                }
            }
        }
    }
}
