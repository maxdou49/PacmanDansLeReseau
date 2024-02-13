package serveur.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import serveur.controller.ControllerPacmanGameServeur;
import serveur.model.Strategie.ListeStrategie;

public class Serveur {
    public static void main(String[] args)
    {
        int p=1234; // le port d’écoute
        ServerSocket ecoute;
        Socket so;

        try {
            ecoute = new ServerSocket(p); // on crée le serveur
            System.out.println("serveur mis en place ");
            while(true) {// le serveur va attendre qu’une connexion arrive
                so = ecoute.accept();
                System.out.println("Client connecté");
                ControllerPacmanGameServeur gestionnaire = new ControllerPacmanGameServeur("layout/openClassic.lay");
                gestionnaire.ajouterJoueur(so);
                gestionnaire.setStrategieFantome(ListeStrategie.RANDOM);
                gestionnaire.setStrategiePacman(ListeStrategie.KEYBOARD);
                gestionnaire.lancer();
            }
        } catch (IOException e) { System.out.println("problème\n"+e); }
    
    }
}
