package xmlSAXParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by LeskovskijE on 6/19/2017.
 */
public class EventHandler extends DefaultHandler {

    boolean bAge = false;
    boolean bID = false;
    boolean bIsDegree = false;
    boolean bName = false;
    boolean bSurName = false;
    boolean bPeople = false;
    People el ; //= new People();
    ArrayList<People> list = new ArrayList<>();


    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML...");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End document");
        for(People e: list) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void startElement(String uri, String name,
                             String qName, Attributes atts) throws SAXException {
        if (qName.equalsIgnoreCase("age")) {
            bAge = true;
        } else if (qName.equalsIgnoreCase("id")) {
            bID = true;
        } else if (qName.equalsIgnoreCase("isDegree")) {
            bIsDegree = true;
        } else if (qName.equalsIgnoreCase("name")) {
            bName = true;
        } else if (qName.equalsIgnoreCase("surname")) {
            bSurName = true;
        } else if (qName.equalsIgnoreCase("element")) {
            el = new People();
            System.out.println("Start Element :" + qName);
        } else if (qName.equalsIgnoreCase("people")) {
            bPeople = true;
            System.out.println("Start Element :" + qName);
        }
    }

    @Override
    public void endElement(String uri, String name, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("element")) {
            list.add(el);
            System.out.println("End Element :" + qName);
        } else if (qName.equalsIgnoreCase("people")) {
            bPeople = false;
            System.out.println("Start Element :" + qName);
        }
    }

    @Override
    public void characters(char ch[],
                           int start, int length) throws SAXException {
        if (bAge&&bPeople) {
            el.setAge(new Integer(new String(ch, start, length)));
            System.out.println("Age: " + el.getAge());
            bAge = false;
        } else if (bID&&bPeople) {
            el.setId(new Integer(new String(ch, start, length)));
            System.out.println("ID: " + el.getId());
            bID = false;
        } else if (bIsDegree&&bPeople) {
            if (new String(ch, start, length).equals("true")) {
                el.setDegree(true);
            }
            System.out.println("isDegree: " + el.isDegree());
            bIsDegree = false;
        } else if (bName) {
            if (bPeople) {
                el.setName(new String(ch, start, length));
                System.out.println("Name: " + el.getName());
            }else {
                System.out.println("Data Model Name: " + new String(ch, start, length));
            }
            bName = false;
        } else if (bSurName&&bPeople) {
            el.setSurname(new String(ch, start, length));
            System.out.println("Surname: " + el.getSurname());
            bSurName = false;
        }
    }
}
