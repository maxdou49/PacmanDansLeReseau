package client.view.menu;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.controller.MenuControlleur;

public class MenuConnexion extends MenuPanel {
    JButton connexion;
    JTextField serveur;
    JTextField utilisateur;
    JTextField motdepasse;

    public MenuConnexion(MenuControlleur controlleur) {
        
        super(controlleur);
        
        try {
            background = new ImageIcon("src/icons/imgCo.jpg").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        setLayout(new GridLayout(4,1));
        serveur = new JTextField("localhost");
        utilisateur = new JTextField();
        motdepasse = new JTextField();
        connexion = new JButton("Connexion", new ImageIcon("src/icons/icnCo.jpg"));

        JPanel serverPanel = new JPanel(new GridLayout(1, 2));
        serverPanel.setOpaque(false);
        JLabel serveurLabel = new JLabel("Serveur : ", JLabel.CENTER);
        custumizeLabel(serveurLabel);
        serverPanel.add(serveurLabel);
        serverPanel.add(serveur);

        JPanel userPanel = new JPanel(new GridLayout(1, 2));
        userPanel.setOpaque(false);
        JLabel utilisateurLabel = new JLabel("Utilisateur : ", JLabel.CENTER);
        custumizeLabel(utilisateurLabel);
        userPanel.add(utilisateurLabel);
        userPanel.add(utilisateur);

        JPanel passPanel = new JPanel(new GridLayout(1, 2));
        passPanel.setOpaque(false);
        JLabel motdepasseLabel = new JLabel("Mot de passe : ", JLabel.CENTER);
        custumizeLabel(motdepasseLabel);
        passPanel.add(motdepasseLabel);
        passPanel.add(motdepasse);
        
        custumizeTextField(serveur);
        custumizeTextField(utilisateur);
        custumizeTextField(motdepasse);
        custumizeButton(connexion);
        this.add(serverPanel);
        this.add(userPanel);
        this.add(passPanel);
        this.add(connexion);

        connexion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                controlleur.connexion(serveur.getText(), utilisateur.getText(), motdepasse.getText());
            }

        });
    }

    public boolean onKeyPress(KeyEvent event)
    {
        return false;
    }
}
