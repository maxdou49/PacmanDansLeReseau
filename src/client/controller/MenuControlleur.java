package client.controller;

import java.net.Socket;

import client.view.menu.MenuMain;
import client.view.menu.MenuStarting;
import client.view.menu.MenuView;
import model.MethodeFactory;

public class MenuControlleur {
    private MenuView menu;

    public MenuControlleur(String args[])
    {
        setScreen(new MenuMain(this));
    }

    public void showCurrent()
    {
        menu.setVisible(true);
    }

    public void hideCurrent()
    {
        menu.setVisible(false);
    }

    public void setScreen(MenuView menu)
    {
        boolean visible = false;
        if(this.menu != null) {
            visible = this.menu.isVisible();
            this.menu.setVisible(false);
        }
        this.menu = menu;
        this.menu.setVisible(visible);
    }

    public void startGame()
    {
        int p=1234; // le port d’écoute
        Socket so;
        
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

    public void multiplayer() {
        hideCurrent();
        
    }
}
