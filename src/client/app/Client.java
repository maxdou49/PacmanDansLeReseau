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
            String message = entree.readLine();
            ObjectMapper objectMapper = new ObjectMapper();

            if(message.equals("Lance")) {
                controleur.play();

                Random r = new Random();
                while(so.isConnected()) {
                    Thread.sleep(1000);
                    AgentAction action = new AgentAction(r.nextInt(4));
                    sortie.println(objectMapper.writeValueAsString(action));
                }
            }

        } catch (IOException e) { System.out.println("problème\n"+e); }
        catch (Exception e) { System.out.println("problème\n"+e); }
    }
}
