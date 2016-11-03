package com.epam.kim.parser;

import com.epam.kim.entity.Product;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class DOMParser {
    public static void parse () {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("Error with builder");
            e.printStackTrace();
        }

        //TODO fix this error
        Document document = null;
        try {
            document = builder.parse(ClassLoader.getSystemResourceAsStream("src\\main\\resources\\xml\\products.xml"));
        } catch (SAXException e) {
            System.out.println("Error with SAXParser");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Error");
            e.printStackTrace();
        }
        ArrayList<Product> prodList = new ArrayList<Product>();

        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = (Node) nodeList.item(i);
            if (node instanceof Element) {
                Product prod = new Product();

                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node cNode = (Node) childNodes.item(j);

                    if (cNode instanceof Element) {
                        String content = cNode.getLastChild().
                                getTextContent().trim();
                        String s = cNode.getNodeName();
                        if (s.equals("name")) {
                            prod.setName(content);

                        } else if (s.equals("price")) {
                            prod.setPrice(Integer.parseInt(content));

                        } else if(s.equals("id")){
                            prod.setId(Byte.parseByte(content));
                        }
                    }
                }
                prodList.add(prod);
            }

        }

        //Printing the Employee list populated.
        for (Product prod : prodList) {
            System.out.println(prod);
        }

    }

}
