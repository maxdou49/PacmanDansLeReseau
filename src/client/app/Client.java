package client.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

import client.controller.ControllerPacmanGameClient;
import model.AgentAction;

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

            ControllerPacmanGameClient controleur = new ControllerPacmanGameClient("layout/originalClassic.lay", so);
            controleur.play();
            
        } catch (IOException e) { System.out.println("problème\n"+e);
        e.printStackTrace(); }
        catch (Exception e) { System.out.println("problème\n"+e); 
        e.printStackTrace();}
    }
}
