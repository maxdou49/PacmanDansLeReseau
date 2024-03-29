package client.model.etatClient;

import client.controller.MainControlleur;
import model.Joueur;
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
            controller.confirmationConnexion(objectMapper.readValue(m.getData(), Joueur.class));
        }
        else
        {
            super.lireMessage(m);
        }
    }
}
