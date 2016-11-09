package com.epam.kim.parser;


import com.epam.kim.entity.Bucket;
import com.epam.kim.entity.Product;
import org.slf4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class StAXParser {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(StAXParser.class);
    public static void parse() {
        log.debug("Start StAX-parsing");
        Product prod = new Product();
        Bucket bucket = new Bucket();
        String qName = "";
        boolean bId = false;
        boolean bName = false;
        boolean bPrice = false;
        boolean bManufacturer = false;
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(
                            new FileReader("src\\main\\resources\\xml\\products.xml"));

            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();
                switch(event.getEventType()){
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("product")) {

                        } else if (qName.equalsIgnoreCase("id")) {
                            bId = true;
                        } else if (qName.equalsIgnoreCase("name")) {
                            bName = true;
                        } else if (qName.equalsIgnoreCase("price")) {
                            bPrice = true;
                        }else if (qName.equalsIgnoreCase("manufacturer")){
                            bManufacturer = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if(bId){
                            prod.setId(Byte.parseByte(characters.getData()));
                            bId = false;
                        }
                        if(bName){
                            prod.setName(characters.getData());
                            bName = false;
                        }
                        if(bPrice){
                            prod.setPrice(Integer.parseInt(characters.getData()));
                            bPrice = false;
                        }
                        if (bManufacturer){
                            prod.setManufacturer(characters.getData());
                            bManufacturer=false;
                        }

                        break;
                    case  XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        if(endElement.getName().getLocalPart().equalsIgnoreCase("product")){
                            bucket.addProduct(new Product(prod.getId(),prod.getName(),prod.getPrice(),prod.getManufacturer()));
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        for (Product product : bucket.getProductList()) {
            log.debug(product.toString());
        }

        log.debug("Stop StAX-parsing");
    }
}