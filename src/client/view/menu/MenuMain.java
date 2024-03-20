package client.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import client.controller.MenuControlleur;

public class MenuMain extends MenuPanel {
    Image background;
    JButton multiButton;
    JButton soloButton;

    public MenuMain(MenuControlleur controlleur)
    {
        super(controlleur);
        
        try {
            background = new ImageIcon("src/icons/imgAcc.jpg").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //this.setLayout(new GridLayout(3,2));

        JButton multiButton = new JButton("Multiplayer");
        JButton soloButton = new JButton("Solo");

        multiButton.setContentAreaFilled(false);
        multiButton.setBorderPainted(false);
        soloButton.setContentAreaFilled(false);
        soloButton.setBorderPainted(false);
        
        multiButton.setForeground(Color.ORANGE);
        multiButton.setFont(new Font("Arial", Font.PLAIN, 26));
        soloButton.setForeground(Color.ORANGE);
        soloButton.setFont(new Font("Arial", Font.PLAIN, 26));

        JButton btnVide1 = new JButton(); btnVide1.setVisible(false);
        JButton btnVide2 = new JButton(); btnVide2.setVisible(false);
        this.setLayout(new GridLayout(3, 2));
        this.add(multiButton);
        this.add(soloButton);
        this.add(btnVide1);
        this.add(btnVide2);

        soloButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlleur.startGame();
                controlleur.hideCurrent();
            }

        });

        multiButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlleur.multiplayer();
                controlleur.hideCurrent();
            }

        });
    }

    public boolean onKeyPress(KeyEvent event)
    {
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
