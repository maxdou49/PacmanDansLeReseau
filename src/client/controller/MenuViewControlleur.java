package client.controller;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.databind.ObjectMapper;

import client.view.menu.MenuView;
import client.view.menu.MenuPanel;
import controller.AbstractControlleur;
import model.Transfert.MessageBuilder;
import model.Transfert.MessageLancer;
import client.model.etatClient.EtatClientAttente;

public class MenuViewControlleur extends AbstractControlleur {
    String serverName = "localhost";
    private MenuView menu;
    private MainControlleur controlleur;

    public MenuViewControlleur(MainControlleur controlleur)
    {
        menu = new MenuView(this);
        menu.setVisible(true);
        this.controlleur = controlleur;
    }

    public void showCurrent()
    {
        menu.setVisible(true);
    }

    public void hideCurrent()
    {
        menu.setVisible(false);
    }

    public void setScreen(MenuPanel view)
    {
        menu.setView(view);
    }

    public void startGame()
    {
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            //Démarrage de la partie
            MessageLancer msg = new MessageLancer("testMaze");
            controlleur.envoyerMessage(MessageBuilder.build("LANCER", mapper.writeValueAsString(msg)));
            //On se met en attente
            controlleur.setEtat(new EtatClientAttente(controlleur));
            
        } catch (Exception e) {
            System.out.println("Client\t"+e); 
            e.printStackTrace();
        }
    }

    public void multiplayer() {
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            //Démarrage de la partie
            MessageLancer msg = new MessageLancer("openClassic_twoPacman"); //On lance sur une map qui a deux joueurs
            controlleur.envoyerMessage(MessageBuilder.build("LANCER", mapper.writeValueAsString(msg)));
            //On se met en attente
            controlleur.setEtat(new EtatClientAttente(controlleur));
            
        } catch (Exception e) {
            System.out.println("Client\t"+e); 
            e.printStackTrace();
        }
        
    }

    public void accesListe()
    {
        try {
            //Récupération liste
            controlleur.envoyerMessage(MessageBuilder.build("LISTER", ""));
            
        } catch (Exception e) {
            System.out.println("Client\t"+e); 
            e.printStackTrace();
        }
    }

    public void connexion(String serveur, String utilisateur, String motdepasse)
    {
        try
        {
            int p=1234; // le port d’écoute
            serverName = serveur;
            if(controlleur.connexionServeur(serverName, p, utilisateur, motdepasse)) {
                //menu.setView(new MenuMain(this));
            } else {
                JOptionPane.showMessageDialog(null, "Connexion impossible !", "Erreur - Connexion", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Connexion impossible");
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public MainControlleur getControlleur()
    {
        return controlleur;
    }
}
