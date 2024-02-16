package client.view.menu;

import java.awt.Dimension;

import javax.swing.JFrame;

import client.controller.MenuControlleur;

public abstract class MenuView extends JFrame {
    protected MenuControlleur controlleur;

    public MenuView(MenuControlleur controlleur)
    {
        this.controlleur = controlleur;
        setTitle("Pacman");
        setSize(new Dimension(700,700));
    }
}
