package test.sergeyvalyushko.store;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Processor processor = new Processor();
        Catalog catalog = Catalog.getInstance("fromDb");
        System.out.println("To display original catalog enter 'show'");
        System.out.println("To sort products by 'name' or 'price' enter parameter name");
        System.out.println("To display top 3 products in every category enter 'top'");
        System.out.println("To select product enter category number then product number");
        System.out.println("To finish enter 'exit'");
        Scanner in = new Scanner(System.in);
        while (1 > 0) {
            String input = in.next();
            processor.run(input, catalog);
        }
    }
}