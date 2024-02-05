import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import controller.ControleurPacmanGame;

public class Client {
    public static void main(String[] argu)
    {
        //ControleurPacmanGame game = new ControleurPacmanGame("layout/originalClassic.lay");
        //game.play();

        int p=1234; // le port d’écoute
        Socket so;

        BufferedReader entree;
        PrintWriter sortie;

        try {
            so = new Socket("localhost", p);

            entree = new BufferedReader(new InputStreamReader(so.getInputStream()));
            sortie = new PrintWriter (so.getOutputStream(), true); 

            String message = entree.readLine();

            if(message.equals("Lance"))
                System.out.println("Lancement du jeu");
                
        } catch (IOException e) { System.out.println("problème\n"+e); }
    }
}
