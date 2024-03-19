package client.model.etatClient;

import client.controller.MainControlleur;
import model.Transfert.Message;

public class EtatClientConnexion extends EtatClient {
    public EtatClientConnexion(MainControlleur controlleur)
    {
        super(controlleur);
    }

    public void lireMessage(Message m) throws Exception
    {
        if(m.getType().equals("VALIDE"))
        {
            controller.confirmationConnexion();
        }
        else
        {
            super.lireMessage(m);
        }
    }
}
