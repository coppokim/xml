package com.epam.kim;

import com.epam.kim.parser.DOMParser;
import com.epam.kim.parser.SaxParser;
import com.epam.kim.parser.StAXParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, XMLStreamException {
        SaxParser.parse();
        DOMParser.parse();
        StAXParser.parse();
    }
}
