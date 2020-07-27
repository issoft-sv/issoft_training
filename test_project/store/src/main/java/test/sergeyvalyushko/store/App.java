package test.sergeyvalyushko.store;

import com.github.javafaker.Faker;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Catalog catalog = Catalog.getInstance();
        catalog.displayCatalog("init", "");
        System.out.println("To sort products by 'name' or 'price' enter parameter name");
        System.out.println("To display top 3 products in every category enter 'top'");
        System.out.println("To finish enter 'exit'");
        Scanner in = new Scanner(System.in);
        while (1 > 0) {
            String input = in.next();
            switch (input){
                case ("exit"): System.exit(0);
                case ("name"): catalog.displayCatalog("sorted", input); break;
                case ("price"): catalog.displayCatalog("sorted", input); break;
                case ("top"): catalog.displayCatalog("top", ""); break;
                default: System.out.println("Incorrect input"); break;
            }
        }
    }
}