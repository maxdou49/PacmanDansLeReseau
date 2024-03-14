package serveur.controller;

import java.io.IOException;
import java.net.Socket;

import model.MethodeFactory;
import model.ReaderWriter;
import model.Transfert.Message;
import model.Transfert.MessageBuilder;
import model.Transfert.MessageLancer;
import serveur.controller.etatClient.EtatClient;
import serveur.controller.etatClient.EtatClientAttente;
import serveur.controller.etatClient.EtatClientJeu;
import serveur.model.Strategie.ListeStrategie;

public class ControlleurClient {
    ReaderWriter clientRW;
    Socket socket;
    EtatClient etat;
    ControllerPacmanGameServeur game;

    public ControlleurClient(Socket client) throws IOException
    {
        this.socket = client;
        clientRW = new ReaderWriter(client);
        setEtat(new EtatClientAttente(this));
    }

    public void run()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String rd;
                    do {
                        rd = clientRW.getReader().readLine();
                        if(rd != null)
                        {
                            System.out.println(rd);
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

    public void setEtat(EtatClient etat)
    {
        this.etat = etat;
        System.out.println(etat);
    }

    public ControllerPacmanGameServeur getGame()
    {
        return game;
    }

    public void lancerPartie(MessageLancer parametres)
    {
        System.out.println("Démarrage sur "+parametres.getCarte());
        game = new ControllerPacmanGameServeur("layout/"+parametres.getCarte()+".lay");
        game.ajouterJoueur(socket);
        game.envoyerEtat(game.getGame().getEtat());
        game.setStrategieFantome(ListeStrategie.RANDOM);
        game.setStrategiePacman(ListeStrategie.KEYBOARD);
        game.lancer();
        setEtat(new EtatClientJeu(this));
    }
}
