/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrambot;

import apitelegramlib.TMessage;
import apitelegramlib.TUpdate;
import apitelegramlib.TelegramAPI;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author mornatta_samuele
 */
public class ThreadBot extends Thread {
    Condivisa cond;
    TelegramAPI api;
    public ThreadBot() throws IOException {
        Condivisa cond = Condivisa.getInstance();
        api = cond.getApi();
    }
    @Override
    public void run() {
        try {
            api.getUpdates();
            ArrayList<Utente> utenti = Utils.getUtenti("utenti.csv");
            while (true) {
                ArrayList<TUpdate> list = api.getUpdates();
                for (int i = 0; i < list.size(); i++) {
                    TUpdate update = list.get(i);
                    TMessage mess = update.getMess();
                    if (mess.getText().toLowerCase().startsWith("/citta")) {
                        Place citta = MapSearch.search(mess.getText().split("/citta ")[1]);
                        if (citta != null) {

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
                                utenti.get(pos).setLon(citta.getLon());
                                api.sendMessage(mess.getChat().getId(), "Utente aggiornato nella seguente posizione:");
                                api.sendPhoto(mess.getChat().getId(), MapSearch.getImage(citta.getLat(), citta.getLon()));
                                Utils.saveUtenti("utenti.csv", utenti);
                            } else {
                                Utente nuovoUtente = new Utente(mess.getChat().getId(), mess.getFrom().getFirst_name(), citta.getLat(), citta.getLon());
                                utenti.add(nuovoUtente);
                                api.sendMessage(mess.getChat().getId(), "Utente registrato nella seguente posizione:");
                                api.sendPhoto(mess.getChat().getId(), MapSearch.getImage(citta.getLat(), citta.getLon()));
                                Utils.saveUtenti("utenti.csv", utenti);
                            }
                        } else {
                            api.sendMessage(mess.getChat().getId(), "Errore città non esiste");
                        }
                    } else if (mess.getText().toLowerCase().startsWith("/help")) {
                        api.sendMessage(mess.getChat().getId(), "COMANDI DISPONIBILI:"
                                + "\n- /citta [nome citta] -> registra o aggiorna la tua posizione per ricevere pubblicità più convegnenti");
                    } else if (mess.getText().toLowerCase().startsWith("/")) {
                        api.sendMessage(mess.getChat().getId(), "Comando non riconosciuto");
                    }
                }
                Thread.sleep(3000);
            }
        } catch (IOException ex) {
            Logger.getLogger(ThreadBot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ThreadBot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ThreadBot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadBot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean spreadPubblicita(String contenuto, String citta, int raggio) throws IOException, FileNotFoundException, ParserConfigurationException, SAXException {
        Place place = MapSearch.search(citta);
        if (place != null) {
            ArrayList<Utente> utenti = Utils.getUtenti("utenti.csv");
            for (int i = 0; i < utenti.size(); i++) {
                // ACOS( SIN(lat1)*SIN(lat2) + COS(lat1)*COS(lat2)*COS(lon2-lon1) ) * 6371000
                Utente utente = utenti.get(i);
                double distanza = Math.acos(Math.sin(place.getLat()) * Math.sin(utente.getLat()) + Math.cos(place.getLat()) * Math.cos(utente.getLat()) * Math.cos(utente.getLon() - place.getLon())) * 6371000;
                System.out.println(raggio);
                if (distanza < raggio) {
                    api.sendMessage(utente.getIdChat(), "Nuova Pubblicita:\n" + contenuto);
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
