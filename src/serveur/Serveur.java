import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import controller.ControleurPacmanGame;

public class Serveur {
    public static void main(String[] args)
    {
        //ControleurPacmanGame game = new ControleurPacmanGame("layout/originalClassic.lay");
        //game.play();

        int p=1234; // le port d’écoute
        ServerSocket ecoute;
        Socket so;
        BufferedReader entree;
        PrintWriter sortie;
        
        try {
            ecoute = new ServerSocket(p); // on crée le serveur
            System.out.println("serveur mis en place ");
            while(true) {// le serveur va attendre qu’une connexion arrive
                so = ecoute.accept();
                System.out.println("Client connecté");
                entree = new BufferedReader(new InputStreamReader(so.getInputStream()));
                sortie = new PrintWriter (so.getOutputStream(), true);    
                sortie.println("Lance");
            }
        } catch (IOException e) { System.out.println("problème\n"+e); }
    
    }
}
