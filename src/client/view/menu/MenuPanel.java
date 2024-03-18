package client.view.menu;

import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import client.controller.MenuControlleur;

public abstract class MenuPanel extends JPanel {
    protected MenuControlleur controlleur;

    public MenuPanel(MenuControlleur controlleur)
    {
        this.controlleur = controlleur;
    }

    public boolean onKeyPress(KeyEvent e)
    {
        return false;
    }
}
