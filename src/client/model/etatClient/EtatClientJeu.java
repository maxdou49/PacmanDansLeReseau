package client.model.etatClient;

import model.Transfert.EtatGame;
import model.Transfert.Message;
import client.controller.ControllerPacmanGameClient;
import client.controller.MainControlleur;
import controller.GameControlleur;

public class EtatClientJeu extends EtatClient {
    public EtatClientJeu(MainControlleur controlleur)
    {
        super(controlleur);
    }

    public void lireMessage(Message m) throws Exception
    {
        if(m.getType().equals("ETAT"))
        {
            GameControlleur game = controller.getGame();
            if(game instanceof ControllerPacmanGameClient)
            {
                ((ControllerPacmanGameClient)game).setEtatGame(objectMapper.readValue(m.getData(), EtatGame.class));
            }
        }
        else
        {
            super.lireMessage(m);
        }
    }
}
