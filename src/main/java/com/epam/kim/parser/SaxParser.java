package com.epam.kim.parser;

import com.epam.kim.entity.Product;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaxParser extends DefaultHandler{
    Product prod = new Product();
    String thisElement = "";
    private static final Logger log = LoggerFactory.getLogger(SaxParser.class);

    public Product getResult(){
        return prod;
    }

    @Override
    public void startDocument() {
        System.out.println("Start SAX-parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) {
        thisElement = qName;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
        thisElement = "";
    }
    @Override
    public void characters(char[] ch, int start, int length)  {
        if (thisElement.equals("id")) {
            prod.setId(new Byte(new String(ch, start, length)));
        }
        if (thisElement.equals("name")) {
            prod.setName(new String(ch, start, length));
        }
        if (thisElement.equals("price")) {
            prod.setPrice(new Integer(new String(ch, start, length)));
            System.out.println(prod);
        }
    }

    @Override
    public void endDocument() {
        System.out.println("Stop SAX-parse XML...");
        System.out.println();
        System.out.println();
        System.out.println();
    }
}

