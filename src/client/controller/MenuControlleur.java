package client.controller;

import java.net.Socket;

import client.view.menu.MenuMain;
import client.view.menu.MenuPanel;
import client.view.menu.MenuStarting;
import client.view.menu.MenuView;
import model.MethodeFactory;

public class MenuControlleur {
    private MenuPanel menu;

    public MenuControlleur(String args[])
    {
        menu = new MenuPanel(this, new MenuMain(this));
    }

    public void showCurrent()
    {
        menu.setVisible(true);
    }

    public void hideCurrent()
    {
        menu.setVisible(false);
    }

    public void setScreen(MenuView view)
    {
        menu.setView(view);
    }

    public void startGame()
    {
        int p=1234; // le port d’écoute
        Socket so;
        
        System.out.println("Ouverture de starting");
        setScreen(new MenuStarting(this));
        try {
            so = new Socket("localhost", p);
            ControllerPacmanGameClient controleur = new ControllerPacmanGameClient(so);
            controleur.play();
            hideCurrent();
        } catch (Exception e) {
            System.out.println(new MethodeFactory().constructMessage("Client\t"+e)); 
            e.printStackTrace();
        }
    }
}
