package client.model.etatClient;

import model.Transfert.EtatGame;
import model.Transfert.Message;
import client.controller.MainControlleur;

public class EtatClientAttente extends EtatClient {
    public EtatClientAttente(MainControlleur controlleur)
    {
        super(controlleur);
    }

    public void lireMessage(Message m) throws Exception
    {
        if(m.getType().equals("ETAT"))
        {
            controller.lancerPartie(objectMapper.readValue(m.getData(), EtatGame.class));
        }
        else
        {
            super.lireMessage(m);
        }
    }
}
