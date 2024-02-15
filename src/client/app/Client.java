package client.app;

import java.io.IOException;
import java.net.Socket;
import client.controller.ControllerPacmanGameClient;

public class Client {
    public static void main(String[] argu) throws InterruptedException
    {
        int p=1234; // le port d’écoute
        Socket so;
        
        try {
            so = new Socket("localhost", p);
            ControllerPacmanGameClient controleur = new ControllerPacmanGameClient("layout/originalClassic.lay", so);
            controleur.play();
            
        } catch (IOException e) { System.out.println("problème\n"+e);
        e.printStackTrace(); }
        catch (Exception e) { System.out.println("problème\n"+e); 
        e.printStackTrace();}
    }
}
