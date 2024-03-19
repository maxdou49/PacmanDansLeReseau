package serveur.controller.etatClient;

import model.Transfert.Message;
import model.Transfert.MessageConnexion;
import serveur.controller.ControlleurClient;

public class EtatClientConnexion extends EtatClient {
    public EtatClientConnexion(ControlleurClient controlleur)
    {
        super(controlleur);
    }

    public void lireMessage(Message m) throws Exception
    {
        if(m.getType().equals("LOGIN"))
        {
            MessageConnexion msg = objectMapper.readValue(m.getData(), MessageConnexion.class);
            controller.validerConnexion(msg.getUtilisateur(), msg.getMotdepasse());
        }
        else
        {
            super.lireMessage(m);
        }
    }
}
