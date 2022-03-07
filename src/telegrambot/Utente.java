/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrambot;

/**
 *
 * @author samue
 */
public class Utente {

    String idChat;
    String nome;
    double lat, lon;
    public Utente() {
    }
    public Utente(String idChat, String nome, double lat, double lon) {
        this.idChat = idChat;
        this.nome = nome;
        this.lat = lat;
        this.lon = lon;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
    
    public String toCsv(){
        return idChat + ";" + nome + ";" + lat + ";" + lon + ";";
    }
    
    public void fromCsv(String csv){
        String[] params = csv.split(";");
        this.idChat = params[0];
        this.nome = params[1];
        this.lat = Double.valueOf(params[2]);
        this.lon = Double.valueOf(params[3]);
    }

    public String getIdChat() {
        return idChat;
    }

    public void setIdChat(String idChat) {
        this.idChat = idChat;
    }
}
