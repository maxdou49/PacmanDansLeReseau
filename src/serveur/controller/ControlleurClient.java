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

public class ControlleurClient {
    ReaderWriter clientRW;
    Socket socket;
    EtatClient etat;
    ControllerPacmanGameServeur game;
    int joueur;

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
                            Message msg = MessageBuilder.buildFromString(rd);
                            etat.lireMessage(msg);
                        }
                    } while((rd != null));
                } catch (Exception e) {
                    System.out.println(new MethodeFactory().constructMessage("ControllerClient\t"+e));
                    e.printStackTrace();
                }
                //On pense a enlever le joueur de la partie quand il quitte
                game.enleverJoueur(joueur);
            }
        }).start();
    }

    public void setEtat(EtatClient etat)
    {
        System.out.println(etat);
        this.etat = etat;
    }

    public ControllerPacmanGameServeur getGame()
    {
        return game;
    }

    //Rejoint une partie
    public void preparerPartie(MessageLancer parametres)
    {
        System.out.println("Démarrage sur "+parametres.getCarte());
        game = ControllerPacmanGameServeur.chercherPartie(parametres);
        joueur = game.ajouterJoueur(this);

        //On lance la partie s'il elle est remplie
        System.out.println("Joueurs " + game.getNombreJoueurs() + "/" + game.getGame().getNbPlayers());
        if(game.assezRempli())
        {
            game.lancer();
            for(ControlleurClient client: game.getJoueurs())
            {
                System.out.println("Démarrage pour " + client);
                if(client != null)
                {
                    client.lancerPartie();
                }
            }
        }
    }

    //Lance la partie
    private void lancerPartie()
    {
        setEtat(new EtatClientJeu(this));
    }

    public int getJoueur()
    {
        return joueur;
    }

    public void sendMessage(Message message)
    {
        try
        {
            //System.out.println("Msg: " + message.toString());
            clientRW.getWriter().println(message.toString());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
