package test.sergeyvalyushko.store;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Catalog catalog = new Catalog();
        catalog.displayCatalog();
        System.out.println("To sort products by 'name' or 'price' enter parameter name");
        System.out.println("To display top 3 products in every category enter 'top'");
        System.out.println("To finish enter 'exit'");
        Scanner in = new Scanner(System.in);
        while (1 > 0) {
            String input = in.next();
            if (input.equals("exit")) break;
            else if (input.equals("name") | input.equals("price")) {
                catalog.displaySortedCatalog(input);
            } else if (input.equals("top")) {
                catalog.displayTop();
            } else System.out.println("Incorrect input");
        }
    }
}