package serveur.controller.etatClient;

import controller.GameControlleur;
import model.AgentAction;
import model.Transfert.Message;
import model.Transfert.MessageLancer;
import serveur.controller.ControllerPacmanGameServeur;
import serveur.controller.ControlleurClient;

public class EtatClientJeu extends EtatClient {
    public EtatClientJeu(ControlleurClient controlleur)
    {
        super(controlleur);
    }

    public void lireMessage(Message m) throws Exception
    {
        ControllerPacmanGameServeur g = controller.getGame();
        if(m.getType().equals("ACTION"))
        {
            if(g)
            {
                g.setActionClient(0, objectMapper.readValue(m.getData(), AgentAction.class));
            }
        }
        else
        {
            super.lireMessage(m);
        }
    }
}
