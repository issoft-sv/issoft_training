package test.sergeyvalyushko.store.helpers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;

public class XmlParser {
    private String value;
    public String parseFromXml(String input) throws IOException {
        File file = new File("store/src/main/resources/config.xml");
        String xml = inputStreamToString(new FileInputStream(file));
        ObjectNode node = new XmlMapper().readValue(xml, ObjectNode.class);

        if (node.has(input)) {
            value = node.get(input).textValue();
        } else {
            System.out.println("Config file doesn't contain such parameter");
            System.exit(0);
        }
        return value;
    }
    private String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

}
