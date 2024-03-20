package client.model.etatClient;

import client.controller.MainControlleur;
import model.Transfert.Message;

public class EtatClientConnecte extends EtatClient {
    public EtatClientConnecte(MainControlleur controlleur)
    {
        super(controlleur);
    }

    public void lireMessage(Message m) throws Exception
    {
        return;
    }
}
