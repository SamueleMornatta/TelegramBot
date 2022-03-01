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
        String token = "5116821392:AAFYIDSHD7MXbUjUHmF14vA-X4qsR80VLms";
        TelegramAPI api = new TelegramAPI(token);
        while (true) {
            ArrayList<TUpdate> list = api.getUpdates();
            for (int i = 0; i < list.size(); i++) {
                TUpdate update = list.get(i);
                TMessage mess = update.getMess();
                if (mess.getText().startsWith("/citta")){
                    TChat chat = mess.getChat();
                    api.sendMessage(chat.getId(), MapSearch.search(mess.getText().split(" ")[1]).toString());
                }
            }
            Thread.sleep(3000);
        }
    }
}
