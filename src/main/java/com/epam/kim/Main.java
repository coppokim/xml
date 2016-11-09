package com.epam.kim;

import com.epam.kim.entity.Product;
import com.epam.kim.parser.DOMParser;
import com.epam.kim.parser.SaxParser;
import com.epam.kim.parser.StAXParser;
import com.epam.kim.util.JaxbXML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, XMLStreamException {
        SaxParser.parse();
        DOMParser.parse();
        StAXParser.parse();
        JaxbXML jaxbXML = new JaxbXML();
        Product product = new Product(1,"jeans",10000,"Kazakhstan");

        jaxbXML.writeDocument(product,"EntityOut.xml");
    }
}
