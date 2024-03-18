package client.view.menu;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import client.controller.MenuControlleur;

public class MenuView extends JFrame {
    MenuPanel menu;
    MenuControlleur controlleur;

    public MenuView(MenuControlleur controlleur)
    {
        this.controlleur = controlleur;
        
        setTitle("PacmanGame");
        setSize(new Dimension(400,600));
        setView(new MenuMain(controlleur));
        
        
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
    }

    public void setView(MenuPanel panel)
    {
        System.out.println("Ouverture de " + panel.toString());
        if(menu != null)
        {
            remove(menu);
        }
        
        setContentPane(panel);
        menu = panel;

        repaint();
    }

    @Override
    public void setVisible(boolean b)
    {
        super.setVisible(b);
        if(menu != null)
        {
            menu.setVisible(b);
        }

        repaint();
    }
}
