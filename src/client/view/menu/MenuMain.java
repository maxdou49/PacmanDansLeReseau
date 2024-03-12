package client.view.menu;

import java.awt.event.KeyEvent;

import client.controller.MenuControlleur;

public class MenuMain extends MenuView {
    public MenuMain(MenuControlleur controlleur)
    {
        super(controlleur);
    }

    public boolean onKeyPress(KeyEvent e)
    {
        controlleur.startGame();
        return true;
    }
}
