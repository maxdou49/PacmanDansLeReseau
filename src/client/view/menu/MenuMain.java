package client.view.menu;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import client.controller.MenuControlleur;

public class MenuMain extends MenuPanel {
    Image background;
    JButton multiButton;
    JButton soloButton;

    public MenuMain(MenuControlleur controlleur)
    {
        super(controlleur);
        
        try {
            background = new ImageIcon("../icons/imgAcc.jpg").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton multiButton = new JButton("Multiplayer");
        JButton soloButton = new JButton("Solo");

        this.add(multiButton);
        this.add(soloButton);

        soloButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlleur.startGame();
            }

        });

        multiButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlleur.multiplayer();
            }

        });
    }

    public boolean onKeyPress(KeyEvent event)
    {
        controlleur.startGame();
        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
