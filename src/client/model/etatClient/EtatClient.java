package client.model.etatClient;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Transfert.Message;
import client.controller.MainControlleur;

public class EtatClient {
    protected MainControlleur controller;
    protected ObjectMapper objectMapper;
    
    public EtatClient(MainControlleur controlleur)
    {
        this.controller = controlleur;
        objectMapper = new ObjectMapper();
    }

    public void lireMessage(Message m) throws Exception
    {
        throw new Exception("Message invalide");
    }
}
