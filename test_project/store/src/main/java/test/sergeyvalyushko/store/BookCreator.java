package test.sergeyvalyushko.store;

import test.sergeyvalyushko.store.category.BookCategory;
import test.sergeyvalyushko.store.helpers.Reflection;

public class BookCreator extends Reflection {

    @Override
    public Category createCategory() {
        return new BookCategory();
    }
}
