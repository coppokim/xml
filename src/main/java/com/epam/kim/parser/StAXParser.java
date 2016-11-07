package com.epam.kim.parser;


import com.epam.kim.entity.Product;

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
import java.util.logging.Logger;

public class StAXParser {

    private static final Logger logger = Logger.getLogger( StAXParser.class.getName() );
    public static void parse() {
        System.out.println("Start StAX-parsing");
        Product prod = new Product();
        boolean bId = false;
        boolean bName = false;
        boolean bPrice = false;
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
                        String qName = startElement.getName().getLocalPart();
                        if (qName.equalsIgnoreCase("product")) {
                            System.out.println();
                        } else if (qName.equalsIgnoreCase("id")) {
                            bId = true;
                        } else if (qName.equalsIgnoreCase("name")) {
                            bName = true;
                        } else if (qName.equalsIgnoreCase("price")) {
                            bPrice = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        if(bId){
                            prod.setId(Byte.parseByte(characters.getData()));
                            //logger.log(Level.INFO, "Id[{0}]: ",characters.getData());
                            bId = false;
                        }
                        if(bName){
                            prod.setName(characters.getData());
                            //logger.info("Name: "+ characters.getData());
                            bName = false;
                        }
                        if(bPrice){
                            prod.setPrice(Integer.parseInt(characters.getData()));
                            //logger.info("Price: "+ characters.getData());
                            System.out.println(prod);
                            bPrice = false;
                        }

                        break;
                    case  XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        if(endElement.getName().getLocalPart().equalsIgnoreCase("student")){
                            System.out.println("End Element : student");
                            System.out.println();
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        System.out.println("Stop StAX-parsing");
    }
}
