package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class AbstractHandler {
    protected AbstractHandler nextHandler;

    public AbstractHandler (AbstractHandler nextHandler){
        this.nextHandler = nextHandler;
    }
    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException {
        if (nextHandler != null) {
            nextHandler.handleRequest(input, catalog);
        }
    }
}
