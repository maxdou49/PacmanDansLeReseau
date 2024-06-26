/*
 * Le joueur est en train de jouer dans une partie
 */

package serveur.controller.etatClient;

import model.AgentAction;
import model.Transfert.Message;
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
            if(g != null)
            {
                g.setActionClient(controller.getJoueur(), objectMapper.readValue(m.getData(), AgentAction.class));
            }
        }
        else
        {
            super.lireMessage(m);
        }
    }
}
