package client.controller;

import java.io.IOException;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

import client.model.etatClient.EtatClient;
import client.model.etatClient.EtatClientConnecte;
import client.model.etatClient.EtatClientConnexion;
import client.model.etatClient.EtatClientJeu;
import client.model.etatClient.EtatClientMenu;
import client.view.menu.MenuConnexion;
import client.view.menu.MenuMain;
import controller.GameControlleur;
import model.ReaderWriter;
import model.Transfert.EtatGame;
import model.Transfert.Message;
import model.Transfert.MessageBuilder;
import model.Transfert.MessageConnexion;

public class MainControlleur {
    ReaderWriter rw;
    Socket socket;
    MenuViewControlleur menu;
    GameControlleur game;
    EtatClient etat;
    boolean running;

    public MainControlleur(String args[]) throws IOException
    {
        this.running = true;
        menu = new MenuViewControlleur(this);
        etat = new EtatClientMenu(this);
    }

    public void connexionServeur(String serveur, int port, String utilisateur, String motdepasse) throws IOException
    {
        socket = new Socket(serveur, port);
        rw = new ReaderWriter(socket);

        //On envoie utilisateur/mdp
        ObjectMapper mapper = new ObjectMapper();
        MessageConnexion log = new MessageConnexion(utilisateur, motdepasse);
        Message msg = MessageBuilder.build("LOGIN", mapper.writeValueAsString(log));
        envoyerMessage(msg);
        setEtat(new EtatClientConnexion(getThis()));

        System.out.println(serveur+":"+String.valueOf(port));
    }

    public void confirmationConnexion()
    {
        setEtat(new EtatClientConnecte(getThis()));
        menu.setScreen(new MenuMain(menu));
    }

    public void lancerPartie(EtatGame etat) throws Exception
    {
        game = new ControllerPacmanGameClient(this, etat);
        setEtat(new EtatClientJeu(this));
        game.play();
        menu.hideCurrent();
    }

    public void terminerPartie()
    {
        //On ferme le jeu
        game.close();
        game = null;
        //On retourne au menu
        setEtat(new EtatClientConnecte(getThis()));
        menu.setScreen(new MenuMain(menu));
        menu.showCurrent();
    }

    public void setEtat(EtatClient etat)
    {
        System.out.println(etat);
        this.etat = etat;
    }

    public void envoyerMessage(Message msg)
    {
        try
        {
            rw.getWriter().println(msg.toString());
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public MainControlleur getThis()
    {
        return this;
    }

    public void run()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                running = true;
                while(running)
                {
                    while(rw == null) //On peut avoir un controlleur sans serveur. On attends d'en avoir un dans ce cas
                    {
                        try
                        {
                            Thread.sleep(10);
                        } catch(Exception e)
                        {
                            
                        }
                    }
                    try {
                        String rd;
                        do {
                            rd = rw.getReader().readLine();
                            if(rd != null)
                            {
                                //System.out.println(rd);
                                Message msg = MessageBuilder.buildFromString(rd);
                                etat.lireMessage(msg);
                            }
                        } while((rd != null));
                    } catch (Exception e) {
                        //C'est normal d'avoir une erreur si la partie se termine
                    }
                    //Retour au menu quand on perd la connexion
                    setEtat(new EtatClientMenu(getThis()));
                    menu.setScreen(new MenuConnexion(menu));
                    menu.showCurrent();
                    rw = null;
                }
            }
        }).start();
    }

    public GameControlleur getGame()
    {
        return game;
    }
}
