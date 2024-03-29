package client.view.menu;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.controller.MenuViewControlleur;
import model.Joueur;

public class MenuMain extends MenuPanel {
    JButton multiButton;
    JButton soloButton;

    public MenuMain(MenuViewControlleur controlleur)
    {
        super(controlleur);
        
        try {
            background = new ImageIcon("src/icons/imgAcc.jpg").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JButton multiButton = new JButton("Multiplayer");
        JButton soloButton = new JButton("Solo");
        JButton listerButton = new JButton("Liste parties");
        JButton btnVide1 = new JButton(); btnVide1.setVisible(false);
        JButton btnVide2 = new JButton(); btnVide2.setVisible(false);

        Joueur compte = controlleur.getControlleur().getCompte();
        JPanel infoCompte = new JPanel(new GridLayout(3,1));
        JLabel user = new JLabel("Utilisateur : " + compte.getUtilisateur());
        JLabel score = new JLabel("\nScore : " + compte.getScore());
        JLabel nombrePartie = new JLabel("\nNombre partie : " + compte.getNbGame());
        infoCompte.add(user);
        infoCompte.add(score);
        infoCompte.add(nombrePartie);
        
        custumizeButton(soloButton);
        custumizeButton(multiButton);
        custumizeButton(listerButton);
        custumizePanel(infoCompte);
        custumizeLabel(user, Color.WHITE);
        custumizeLabel(score, Color.WHITE);
        custumizeLabel(nombrePartie, Color.WHITE);
        
        this.setLayout(new GridLayout(3, 2));
        this.add(multiButton);
        this.add(soloButton);
        this.add(btnVide1);
        this.add(btnVide2);
        this.add(listerButton);
        this.add(infoCompte);

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

        listerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlleur.accesListe();
            }

        });
    }

    public boolean onKeyPress(KeyEvent event)
    {
        return false;
    }
}
