package client.view.menu;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client.controller.MenuControlleur;

public abstract class MenuView extends JPanel {
    protected MenuControlleur controlleur;

    public MenuView(MenuControlleur controlleur)
    {
        this.controlleur = controlleur;
    }

    public boolean onKeyPress(KeyEvent e)
    {
        return false;
    }
}
