package client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import client.view.menu.MenuMain;
import client.view.menu.MenuPanel;
import client.view.menu.MenuStarting;
import client.view.menu.MenuView;
import controller.AbstractControlleur;
import model.MethodeFactory;
import model.Transfert.MessageBuilder;
import model.Transfert.MessageLancer;
import client.model.etatClient.EtatClientAttente;

public class MenuControlleur extends AbstractControlleur {
    private MenuPanel menu;
    private MainControlleur controlleur;

    public MenuControlleur(MainControlleur controlleur)
    {
        menu = new MenuPanel(this, new MenuMain(this));
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

    public void setScreen(MenuView view)
    {
        boolean visible = false;
        if(this.menu != null)
        {
            visible = this.menu.isVisible();
            this.menu.setVisible(false);
        }
        this.menu = menu;
        this.menu.setVisible(visible);
    }

    public void startGame()
    {
        ObjectMapper mapper = new ObjectMapper();
        int p=1234; // le port d’écoute
        
        setScreen(new MenuStarting(this));
        try {
            //Connexion au serveur
            controlleur.assignerServeur("localhost", p);
            //Démarrage de la partie
            MessageLancer msg = new MessageLancer("openClassic");
            controlleur.envoyerMessage(MessageBuilder.build("LANCER", mapper.writeValueAsString(msg)));
            //On
            controlleur.setEtat(new EtatClientAttente(controlleur));
            
        } catch (Exception e) {
            System.out.println(new MethodeFactory().constructMessage("Client\t"+e)); 
            e.printStackTrace();
        }
    }

    public void multiplayer() {
        hideCurrent();
        
    }
}
