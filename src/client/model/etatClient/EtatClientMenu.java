package client.model.etatClient;

import client.controller.MainControlleur;
import model.Transfert.Message;

public class EtatClientMenu extends EtatClient {
    public EtatClientMenu(MainControlleur controlleur)
    {
        super(controlleur);
    }

    public void lireMessage(Message m) throws Exception
    {
        return;
    }
}
