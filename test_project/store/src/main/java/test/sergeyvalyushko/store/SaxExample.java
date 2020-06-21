package test.sergeyvalyushko.store;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SaxExample {
    private static Map<String, String> sort = new HashMap<>();
    public Map<String, String> config () throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        AdvancedXMLHandler handler = new AdvancedXMLHandler();
        parser.parse(new File("store/src/main/resources/config.xml"), handler);
        return sort;
    }
    private static class AdvancedXMLHandler extends DefaultHandler {
        private String name, price, rate, lastElementName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {

            String information = new String(ch, start, length);
            information = information.replace("\n", "").trim();
//            System.out.println(information);

            if (!information.isEmpty()) {
                if (lastElementName.equals("name")|lastElementName.equals("price")|lastElementName.equals("rate"))
                    sort.put(lastElementName, information);

            }
        }
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            }
        }
    }
