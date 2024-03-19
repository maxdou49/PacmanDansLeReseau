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

import client.controller.MenuControlleur;

public class MenuConnexion extends MenuPanel {
    Image background;
    JButton connexion;
    TextField serveur;
    TextField utilisateur;
    TextField motdepasse;

    public MenuConnexion(MenuControlleur controlleur) {
        super(controlleur);
        //TODO Auto-generated constructor stub
        try {
            background = new ImageIcon("../icons/imgAcc.jpg").getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new GridLayout(4,1));
        serveur = new TextField("localhost");
        utilisateur = new TextField();
        motdepasse = new TextField();
        connexion = new JButton("Connexion");

        JPanel serverPanel = new JPanel(new GridLayout(2,1));
        serverPanel.add(new JLabel("Serveur : "));
        serverPanel.add(serveur);

        JPanel userPanel = new JPanel(new GridLayout(2,1));
        userPanel.add(new JLabel("Nom d'utilisateur : "));
        userPanel.add(utilisateur);

        JPanel passPanel = new JPanel(new GridLayout(2,1));
        passPanel.add(new JLabel("Mot de passe : "));
        passPanel.add(motdepasse);
        
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
