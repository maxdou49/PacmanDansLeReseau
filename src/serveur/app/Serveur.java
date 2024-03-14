package serveur.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import serveur.controller.ControlleurClient;

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
            
                ControlleurClient gestionnaire = new ControlleurClient(so);        
                gestionnaire.run();
            }
        } catch (IOException e) { System.out.println("problème\n"+e); 
        e.printStackTrace();}
    }
}
