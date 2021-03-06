package test.sergeyvalyushko.store.helpers;

import com.github.javafaker.Faker;
import test.sergeyvalyushko.store.Product;

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
}
