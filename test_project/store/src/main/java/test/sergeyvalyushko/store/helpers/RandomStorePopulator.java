package test.sergeyvalyushko.store.helpers;

import com.github.javafaker.Faker;
import test.sergeyvalyushko.common.Reflection;
import test.sergeyvalyushko.store.Category;
import test.sergeyvalyushko.store.Product;

import java.util.Date;
import java.util.List;

public class RandomStorePopulator {
    private Faker faker = new Faker();
    private List<Product> createData(String categoryName, List<Product> productList) {
        String productName = "";
        for (int i = 0; i < 5; i++) {
            switch (categoryName) {
                case ("Book"):
                    productName = faker.book().title();
                    break;
                case ("Beer"):
                    productName = faker.beer().name();
                    break;
                case ("Food"):
                    productName = faker.food().ingredient();
                    break;
            }
            productList.add(new Product(productName, (int) ((Math.random() * 10) + 1), faker.date().between(new Date(1212121212222L), new Date())));
        }
        return productList;
    }
    public List<Category> populateCatalog(){
        List<Category> categories;
        String location = "test.sergeyvalyushko.store";
        Reflection<Category> reflection = new Reflection<Category>();
        categories = reflection.createClassesInstances(Category.class, location);
        for (Category category : categories) {
            this.createData(category.getName(), category.getProductList());
        } return categories;
    }
}
