/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrambot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author prof
 */
public class MyXMLOperations {
    
    private Document document;

    public Document getDocument() {
        return document;
    }
    
    public void validate(String XMLdocument, String XSDschema) throws SAXException, IOException {
        // creazione di uno schema XSD a partire dal file
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File schemaFile = new File(XSDschema);
        Schema schema = factory.newSchema(schemaFile);
        // creazione di un validatore rispetto allo schema XSD
        Validator validator = schema.newValidator();
        // validazione del documento XML
        Source source = new StreamSource(XMLdocument);
        validator.validate(source);
    }

    public List parseDocument(String filename)
            throws ParserConfigurationException, SAXException, IOException {
        
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Element root, element;
        NodeList nodelist;
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        
        document = builder.parse(filename);
        root = document.getDocumentElement();
        List<Place> dati = new ArrayList();
        Place dato;
        nodelist = root.getElementsByTagName("place");
        if (nodelist != null && nodelist.getLength() > 0) {
            for (int i = 0; i < nodelist.getLength(); i++) {
                element = (Element) nodelist.item(i);
                dato = getInfo(element);
                dati.add(dato);
            }
        }
        return dati;
    }
    
    
    private Place getInfo(Element element) {
        Place place = new Place();
        place.setLat(Double.valueOf(element.getAttributes().getNamedItem("lat").getNodeValue()));
        place.setLon(Double.valueOf(element.getAttributes().getNamedItem("lon").getNodeValue()));
        place.setBuilding(getTextValue(element, "building"));
        place.setAmenity(getTextValue(element, "amenity"));
        place.setRoad(getTextValue(element, "road"));
        place.setTown(getTextValue(element, "town"));
        place.setCounty(getTextValue(element, "county"));
        place.setState(getTextValue(element, "state"));
        place.setPoscode(getTextValue(element, "poscode"));
        place.setCountry(getTextValue(element, "country"));
        place.setCountry_code(getTextValue(element, "country_code"));
        place.setHouse_number(getTextValue(element, "house_number"));
        place.setVillage(getTextValue(element, "village"));
        return place;
    }
    
    // restituisce il valore testuale dell’elemento figlio specificato
    private String getTextValue(Element element, String tag) {
        String value = null;
        NodeList nodelist;
        nodelist = element.getElementsByTagName(tag);
        if (nodelist != null && nodelist.getLength() > 0) {
            value = nodelist.item(0).getFirstChild().getNodeValue();
        }
        return value;
    }
}
