package com.epam.kim.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMParser {
    private static final Logger log = LoggerFactory.getLogger(SaxParser.class);
    public static void parse () {
        log.debug("Start DOM-parsing");
        try {
            File inputFile = new File("src\\main\\resources\\xml\\products.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            log.debug("Root element : "
                    + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("product");
            log.debug("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println(nNode.getNodeName()+":");
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("id : "
                            + eElement
                            .getElementsByTagName("id")
                            .item(0)
                            .getTextContent());
                    System.out.println("Name : "
                            + eElement
                            .getElementsByTagName("name")
                            .item(0)
                            .getTextContent());
                    System.out.println("price : "
                            + eElement
                            .getElementsByTagName("price")
                            .item(0)
                            .getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Stop DOM-parsing");
        System.out.println();
        System.out.println();
        System.out.println();
    }

}
