package com.epam.kim.parser;

import com.epam.kim.entity.Bucket;
import com.epam.kim.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SaxParser extends DefaultHandler{
    private static final Logger log = LoggerFactory.getLogger(SaxParser.class);
    Product prod = new Product();
    Bucket  bucket = new Bucket();
    String thisElement = "";

    public static void parse() throws SAXException, ParserConfigurationException {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            SaxParser saxp = new SaxParser();
            parser.parse(new File("src\\main\\resources\\xml\\products.xml"), saxp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @Override
    public void startDocument() {
        log.debug("Start SAX-parse XML...");
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

        switch (thisElement){
            case "id":
                prod.setId(new Integer(new String(ch, start, length)));
                break;
            case "name":
                prod.setName(new String(ch, start, length));
                break;
            case "price":
                prod.setPrice(new Integer(new String(ch, start, length)));
                break;
            case "manufacturer":
                prod.setManufacturer(new String(ch, start, length));
                Product product = new Product(prod.getId(),prod.getName(),prod.getPrice(),prod.getManufacturer());
                bucket.addProduct(product);
                break;

        }
    }

    @Override
    public void endDocument() {
        for (Product product : bucket.getProductList()) {
            log.debug(product.toString());
        }
        log.debug("Stop SAX-parse XML...");
    }

}

