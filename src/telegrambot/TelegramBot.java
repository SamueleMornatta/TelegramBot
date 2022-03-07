/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrambot;

import apitelegramlib.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author mornatta_samuele
 */
public class TelegramBot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException, FileNotFoundException, ParserConfigurationException, SAXException {
        String token = "";
        TelegramAPI api = new TelegramAPI(token);
        api.getUpdates();
        ArrayList<Utente> utenti = Utils.getUtenti("utenti.csv");
        while (true) {
            ArrayList<TUpdate> list = api.getUpdates();
            for (int i = 0; i < list.size(); i++) {
                TUpdate update = list.get(i);
                TMessage mess = update.getMess();
                if (mess.getText().toLowerCase().startsWith("/citta")) {
                    Place citta = MapSearch.search(mess.getText().split("/citta ")[1]);
                    boolean exists = false;
                    int pos = 0;
                    for (int j = 0; j < utenti.size(); j++) {
                        if (utenti.get(j).getIdChat().equals(mess.getChat().getId())) {
                            exists = true;
                            pos = j;
                        }
                    }
                    if (exists) {
                        utenti.get(pos).setLat(citta.getLat());
                        utenti.get(pos).setLat(citta.getLon());
                        api.sendMessage(mess.getChat().getId(), "Utente aggiornato");
                        Utils.saveUtenti("utenti.csv", utenti);
                    } else {
                        Utente nuovoUtente = new Utente(mess.getChat().getId(), mess.getFrom().getFirst_name(), citta.getLat(), citta.getLon());
                        utenti.add(nuovoUtente);
                        api.sendMessage(mess.getChat().getId(), "Utente registrato");
                        Utils.saveUtenti("utenti.csv", utenti);
                    }
                }
            }
            Thread.sleep(3000);
        }
    }
}
