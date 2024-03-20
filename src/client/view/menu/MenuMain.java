package client.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

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
        soloButton.setContentAreaFilled(false);
        multiButton.setMinimumSize(new Dimension(getParent().getWidth()/2, getHeight()));

        multiButton.setForeground(Color.ORANGE);
        soloButton.setForeground(Color.ORANGE);
        soloButton.setMinimumSize(new Dimension(getParent().getWidth()/2, getHeight()));
        
        this.add(multiButton, BorderLayout.EAST);
        this.add(soloButton, BorderLayout.WEST);

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

        setSize(new Dimension(438, 274));
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
