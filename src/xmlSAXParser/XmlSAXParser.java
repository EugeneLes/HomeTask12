package xmlSAXParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;


public class XmlSAXParser {

    public XmlSAXParser() {
        super();
    }

    public static void main(String[] args)
    {
        // write your code here
        try {
            File inputFile = new File("test.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            EventHandler eventHandler = new EventHandler();
            saxParser.parse(inputFile, eventHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

