/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrambot;

import apitelegramlib.TelegramAPI;
import java.io.IOException;

/**
 *
 * @author mornatta_samuele
 */
public class Condivisa {

    private static Condivisa instance;
    public TelegramAPI api;

    // Costruttore invisibile
    private Condivisa() throws IOException {
        api = new TelegramAPI("5116821392:AAFYIDSHD7MXbUjUHmF14vA-X4qsR80VLms");
    }

    public static synchronized Condivisa getInstance() throws IOException {
        // Crea l'oggetto solo se NON esiste:
        if (instance == null) {
            instance = new Condivisa();
        }
        return instance;
    }

    public TelegramAPI getApi() {
        return api;
    }
    
}
