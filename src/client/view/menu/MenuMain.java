package client.view.menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

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

        setLocationRelativeTo(null);

        // Créer un label pour afficher l'image
        ImageIcon imageIcon = new ImageIcon("icons/imgAcc.jpg"); // Chemin vers l'image
        JLabel background = new JLabel(imageIcon);
        background.setLayout(new BorderLayout());

        this.add(multiButton, BorderLayout.CENTER);
        this.add(soloButton, BorderLayout.CENTER);

        setContentPane(background);

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
}
