package client.app;

import java.net.Socket;
import client.controller.ControllerPacmanGameClient;
import model.MethodeFactory;

public class Client {
    public static void main(String[] argu) throws InterruptedException
    {
        int p=1234; // le port d’écoute
        Socket so;
        
        try {
            so = new Socket("localhost", p);
            ControllerPacmanGameClient controleur = new ControllerPacmanGameClient(so);
            controleur.play();
            
        } catch (Exception e) {
            System.out.println(new MethodeFactory().constructMessage("Client\t"+e)); 
            e.printStackTrace();
        }
    }
}
