package client.view.menu;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import client.controller.MenuControlleur;

public class MenuStarting extends MenuPanel {
    public MenuStarting(MenuControlleur controlleur)
    {
        super(controlleur);
        setLayout(new FlowLayout());
        JLabel texte = new JLabel("Démarrage de la partie");
        add(texte);
    }
}
