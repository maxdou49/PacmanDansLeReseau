package client.view.menu;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import client.controller.MenuControlleur;

public class MenuPanel extends JFrame {
    MenuView menu;
    MenuControlleur controlleur;

    public MenuPanel(MenuControlleur controlleur, MenuView view)
    {
        setTitle("Pacman");
        setSize(new Dimension(700,700));
        setVisible(true);
        
        
        addKeyListener(new KeyListener() { //C'est un peu bugué la gestion des touches dans un sous-panel
            public void keyPressed(KeyEvent e) {
                menu.onKeyPress(e);
            }
        
            public void keyReleased(KeyEvent e) {
                return;
            }
        
            public void keyTyped(KeyEvent e) {
                return;
            }
        });
        setView(view);
    }

    public void setView(MenuView view)
    {
        System.out.println("Ouverture de " + view.toString());
        if(menu != null)
        {
            remove(menu);
        }
        add(view);
        menu = view;
    }
}
