/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrambot;

import apitelegramlib.TUpdate;
import apitelegramlib.TelegramAPI;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mornatta_samuele
 */
public class TelegramBot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        String token = "";
        TelegramAPI api = new TelegramAPI(token);
        ArrayList<TUpdate> list = api.getUpdates();
        for (int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getUpdate_id());
        }
    }
    
}
