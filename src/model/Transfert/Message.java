package model.Transfert;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Stocke un message venant d'un client.
 * Utiliser getData permet de récupérer la classe avec les données.
 */

public class Message {
    private String type;
    private String data;
    ObjectMapper om;
    
    //Commun
    static public final String KEEPALIVE = "KEEPALIVE";
    //Serveur
    static public final String ETAT = "ETAT";
    //Client
    static public final String ACTION = "ACTION"; 

    Message(String type, String data)
    {
        this.type = type;
        this.data = data;
    }

    public String getType()
    {
        return type;
    }

    public String getData()
    {
        return this.data;
    }

    public String toString()
    {
        return String.format("%s %s", type, data);
    }
}
