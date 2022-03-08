/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrambot;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author mornatta_samuele
 */
public class MapSearch {

    public static Place search(String place) throws FileNotFoundException, IOException, ParserConfigurationException, SAXException {
        URL url;
        PrintWriter out = new PrintWriter("xml/gpsdin.xml");
        url = new URL("https://nominatim.openstreetmap.org/search?q=" + URLEncoder.encode(place) + "&format=xml&addressdetails=1&limit=1");
        Scanner s = new Scanner(url.openStream());
        s.useDelimiter("\u001a");
        String file = s.next();
        out.write(file);
        out.close();
        String xmlFile = "xml/gpsdin.xml";
        List<Place> dati = new ArrayList<>();
        MyXMLOperations xml = new MyXMLOperations();
        dati = xml.parseDocument(xmlFile);
        if (dati.size() > 0) {
            return dati.get(0);
        } else {
            return null;
        }
    }

    public static String getImage(double lat, double lon) throws MalformedURLException, IOException {
        return "https://open.mapquestapi.com/staticmap/v4/getmap?key=Fs0tM3ZhpiVMGuTwPxQ1mgcSicdAwXur&size=500,500&zoom=15&center=" + lat + "," + lon;
    }
}
