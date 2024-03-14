package serveur.controller.etatClient;

import model.Transfert.Message;
import model.Transfert.MessageLancer;
import serveur.controller.ControlleurClient;

public class EtatClientAttente extends EtatClient {
    public EtatClientAttente(ControlleurClient controlleur)
    {
        super(controlleur);
    }

    public void lireMessage(Message m) throws Exception
    {
        System.out.println(m.toString());
        System.out.println("\""+m.getType()+"\"");
        if(m.getType().equals("LANCER"))
        {
            controller.lancerPartie(objectMapper.readValue(m.getData(), MessageLancer.class));
        }
        else
        {
            super.lireMessage(m);
        }
    }
}
