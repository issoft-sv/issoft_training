package test.sergeyvalyushko.store;

import test.sergeyvalyushko.store.category.FoodCategory;
import test.sergeyvalyushko.store.helpers.Reflection;

public class FoodCreator extends Reflection {

    @Override
    public Category createCategory() {
        return new FoodCategory();
    }
}
