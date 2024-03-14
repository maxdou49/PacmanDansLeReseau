package client.view.menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

import client.controller.MenuControlleur;

public class MenuMain extends MenuView {
    JButton multiButton = new JButton("Multiplayer");
    JButton soloButton = new JButton("Solo");

    public MenuMain(MenuControlleur controlleur)
    {
        super(controlleur);

        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                controlleur.startGame();
            }
        
            public void keyReleased(KeyEvent e) {
                return;
            }
        
            public void keyTyped(KeyEvent e) {
                return;
            }
        });
    }
}
