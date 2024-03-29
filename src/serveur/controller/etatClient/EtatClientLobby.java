/*
 * Le joueur a rejoint une partie qui n'a pas démarré
 */

package serveur.controller.etatClient;

import model.AgentAction;
import model.Transfert.Message;
import serveur.controller.ControllerPacmanGameServeur;
import serveur.controller.ControlleurClient;

public class EtatClientLobby extends EtatClient {
    public EtatClientLobby(ControlleurClient controlleur)
    {
        super(controlleur);
    }

    public void lireMessage(Message m) throws Exception
    {
        return;
    }
}
