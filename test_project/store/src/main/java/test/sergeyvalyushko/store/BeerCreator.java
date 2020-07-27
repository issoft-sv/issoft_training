package test.sergeyvalyushko.store;

import test.sergeyvalyushko.store.category.BeerCategory;
import test.sergeyvalyushko.store.helpers.Reflection;

public class BeerCreator extends Reflection {

    @Override
    public Category createCategory() {
        return new BeerCategory();
    }
}
