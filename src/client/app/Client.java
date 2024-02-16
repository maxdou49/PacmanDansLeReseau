package client.app;

import java.net.Socket;
import client.controller.ControllerPacmanGameClient;
import client.controller.MenuControlleur;
import model.MethodeFactory;

public class Client {
    public static void main(String[] argu) throws InterruptedException
    {
        MenuControlleur menu = new MenuControlleur(argu);
        menu.showCurrent();
    }
}
