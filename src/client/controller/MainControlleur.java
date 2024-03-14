package client.controller;

import java.io.IOException;
import java.net.Socket;

import client.model.etatClient.EtatClient;
import client.model.etatClient.EtatClientJeu;
import client.model.etatClient.EtatClientMenu;
import controller.GameControlleur;
import model.MethodeFactory;
import model.ReaderWriter;
import model.Transfert.EtatGame;
import model.Transfert.Message;
import model.Transfert.MessageBuilder;

public class MainControlleur {
    ReaderWriter rw;
    Socket socket;
    MenuControlleur menu;
    GameControlleur game;
    EtatClient etat;

    public MainControlleur(String args[]) throws IOException
    {
        menu = new MenuControlleur(this);
        etat = new EtatClientMenu(this);
    }

    public void assignerServeur(String serveur, int port) throws IOException
    {
        socket = new Socket(serveur, port);
        rw = new ReaderWriter(socket);
        System.out.println(serveur+":"+String.valueOf(port));
    }

    public void lancerPartie(EtatGame etat) throws Exception
    {
        game = new ControllerPacmanGameClient(this, etat);
        setEtat(new EtatClientJeu(this));
        game.play();
    }

    public void setEtat(EtatClient etat)
    {
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

    public void run()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
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
                            Message msg = MessageBuilder.buildFromString(rd);
                            etat.lireMessage(msg);
                        }
                    } while((rd != null));
                } catch (Exception e) {
                    System.out.println(new MethodeFactory().constructMessage("ControllerClient\t"+e));
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public GameControlleur getGame()
    {
        return game;
    }
}
