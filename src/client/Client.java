package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.ControleurPacmanGame;
import model.AgentAction;
import model.Pacman;
import model.PacmanGame;
import model.Strategie.ListeStrategie;
import model.Strategie.StrategieAgent;

public class Client {
    public static void main(String[] argu) throws InterruptedException
    {
        int p=1234; // le port d’écoute
        Socket so;

        BufferedReader entree;
        PrintWriter sortie;

        try {
            so = new Socket("localhost", p);

            entree = new BufferedReader(new InputStreamReader(so.getInputStream()));
            sortie = new PrintWriter (so.getOutputStream(), true); 

            String message = entree.readLine();
            ObjectMapper objectMapper = new ObjectMapper();

            if(message.equals("Lance")) {
                ControleurPacmanGame controleur = new ControleurPacmanGame("layout/originalClassic.lay");
                controleur.setStrategiePacman(ListeStrategie.KEYBOARD);
                controleur.play();

                PacmanGame game = (PacmanGame)controleur.getGame();
                Pacman pacmans = game.getPacman();
                StrategieAgent strategie = pacmans.getStrategie();
                while(so.isConnected()) {
                    Thread.sleep(1000);
                    AgentAction action = strategie.getAction();
                    sortie.println(objectMapper.writeValueAsString(action));
                }
            }

        } catch (IOException e) { System.out.println("problème\n"+e); }
    }
}
