package serveur.controller.etatClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Transfert.Message;
import serveur.controller.ControlleurClient;

public class EtatClient {
    protected ControlleurClient controller;
    protected ObjectMapper objectMapper;
    
    public EtatClient(ControlleurClient controlleur)
    {
        this.controller = controlleur;
        objectMapper = new ObjectMapper();
    }

    public void lireMessage(Message m) throws Exception
    {
        throw new Exception("Message invalide : "+m.getType());
    }
}
