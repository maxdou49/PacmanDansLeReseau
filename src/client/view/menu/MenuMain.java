package client.view.menu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import client.controller.MenuControlleur;

public class MenuMain extends MenuView {
    JButton multiButton;
    JButton soloButton;

    public MenuMain(MenuControlleur controlleur)
    {
        super(controlleur);

        JButton multiButton = new JButton("Multiplayer");
        JButton soloButton = new JButton("Solo");

        ImageIcon imageIcon = new ImageIcon("icons/imgAcc.jpg"); // Chemin vers l'image
        JLabel background = new JLabel(imageIcon);
        background.setLayout(new BorderLayout());
        //this.add(background);

        this.add(multiButton, BorderLayout.CENTER);
        this.add(soloButton, BorderLayout.CENTER);

        this.setVisible(true);

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
}
