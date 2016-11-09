package com.epam.kim.util;

import org.slf4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class JaxbXML {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JaxbXML.class);
    public static void writeDocument(Object transformClass, String fileName) {
        JAXBContext entity;
        log.debug("Start writing XML document");
        try {
            entity = JAXBContext.newInstance(transformClass.getClass());
            Marshaller marshaller = entity.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(transformClass, new FileOutputStream(fileName));
        } catch (JAXBException e) {
            log.error("JAXBEXception");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            log.error("File not found");
            e.printStackTrace();
        }
        log.debug("Finished writing XML document");
    }
}
