/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrambot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author samue
 */
public class Utils {

    public static ArrayList<Utente> getUtenti(String filepath) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filepath));
        ArrayList<Utente> utenti = new ArrayList<Utente>();
        while (sc.hasNext()) {
            Utente tmp = new Utente();
            tmp.fromCsv(sc.next());
            utenti.add(tmp);
        }
        return utenti;
    }

    public static void saveUtenti(String filepath, ArrayList<Utente> utenti) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(new File(filepath), "UTF-8");
        String csv = "";
        for (int i = 0; i < utenti.size(); i++){
            csv += utenti.get(i).toCsv() + "\n";
        }
        writer.write(csv);
        writer.flush();
        writer.close();
    }
}
