package test.sergeyvalyushko.store.handlers;

import org.xml.sax.SAXException;
import test.sergeyvalyushko.store.Catalog;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class AbstractHandler {
    protected AbstractHandler nextHandler;
    protected List<String> handledInput;

    public AbstractHandler(AbstractHandler nextHandler){
        this.nextHandler = nextHandler;
    }

    public void handleRequest(String input, Catalog catalog) throws ParserConfigurationException, SAXException, IOException{
        if (nextHandler != null) {
            nextHandler.handleRequest(input, catalog);
        } else System.out.println("Incorrect input");
    }
}
