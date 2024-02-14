package client.test;

import java.io.IOException;
import java.net.Socket;

import client.controller.ControllerPacmanGameClient;

public class Test {
    public static void main(String[] argu) {
        try {
            Socket so = new Socket();
            ControllerPacmanGameClient controleur;
            controleur = new ControllerPacmanGameClient("layout/openClassic.lay", so);
            // controleur.play();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
