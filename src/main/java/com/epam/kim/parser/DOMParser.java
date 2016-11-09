package com.epam.kim.parser;

import com.epam.kim.entity.Bucket;
import com.epam.kim.entity.Product;
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
    private static final Logger log = LoggerFactory.getLogger(DOMParser.class);
    public static void parse () {
        Product prod;
        Bucket bucket = new Bucket();
        log.debug("Start DOM-parsing");
        try {
            File inputFile = new File("src\\main\\resources\\xml\\products.xml");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            //log.debug("Root element : " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("product");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    prod = new Product();
                    Element eElement = (Element) nNode;
                    prod.setId(Byte.parseByte(eElement.getElementsByTagName("id").item(0).getTextContent()));
                    prod.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    prod.setPrice(Integer.parseInt(eElement.getElementsByTagName("price").item(0).getTextContent()));
                    prod.setManufacturer(eElement.getElementsByTagName("manufacturer").item(0).getTextContent());
                    bucket.addProduct(prod);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Product product : bucket.getProductList()) {
            log.debug(product.toString());
        }

        log.debug("Stop DOM-parsing");
    }

}
