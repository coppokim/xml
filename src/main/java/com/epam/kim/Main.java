package com.epam.kim;

import com.epam.kim.parser.SaxParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SaxParser saxp = new SaxParser();

        try {
            parser.parse(new File("src\\main\\resources\\xml\\products.xml"), saxp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
