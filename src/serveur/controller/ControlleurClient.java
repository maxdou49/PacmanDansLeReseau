package serveur.controller;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Joueur;
import model.ReaderWriter;
import model.Transfert.ListePartie;
import model.Transfert.Message;
import model.Transfert.MessageBuilder;
import model.Transfert.MessageLancer;
import serveur.controller.etatClient.EtatClient;
import serveur.controller.etatClient.EtatClientAttente;
import serveur.controller.etatClient.EtatClientConnexion;
import serveur.controller.etatClient.EtatClientJeu;
import serveur.model.CommunicationAPI;

public class ControlleurClient {
    ReaderWriter clientRW;
    Socket socket;
    EtatClient etat;
    ControllerPacmanGameServeur game;
    int joueur;
    Joueur compte;

    public ControlleurClient(Socket client) throws IOException
    {
        this.socket = client;
        clientRW = new ReaderWriter(client);
        setEtat(new EtatClientConnexion(this));
    }

    public void closeConnexion()
    {
        try
        {
            clientRW = null;
            socket.close();
            socket = null;
        } catch(IOException e)
        {
            e.printStackTrace();
        }
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
                            //System.out.println(rd);
                            Message msg = MessageBuilder.buildFromString(rd);
                            etat.lireMessage(msg);
                        }
                    } while((rd != null));
                } catch (Exception e) {
                    //On autorise le serveur a stopper la connexion
                }
                //On pense a enlever le joueur de la partie quand il quitte
                if(game != null)
                {
                    game.enleverJoueur(joueur);
                    game = null;
                }
            }
        }).start();
    }

    //Prévenir l'utilisateur d'un echec
    private void echecConnexion()
    {
        sendMessage(MessageBuilder.build("REFUS", "")); //Peut-être mettre une raison
        closeConnexion();
    }

    //Prevenir l'utilisateur d'une reussite
    private void reussiteConnextion()
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            sendMessage(MessageBuilder.build("VALIDE", mapper.writeValueAsString(compte)));
            setEtat(new EtatClientAttente(this));
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void validerConnexion(String utilisateur, String motdepasse)
    {
        //On teste si on peut se connecter
        try
        {
            //Acces a l'API pour se connecter
            Joueur joueur = CommunicationAPI.connexion(utilisateur, motdepasse);
            System.out.println(joueur);
            //Si on a pas de joueur alors echec
            if(joueur == null)
            {
                echecConnexion();
            }
            System.out.println("Connecté en tant que "+joueur.getUtilisateur());
            compte = joueur;
            reussiteConnextion();
        } catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            echecConnexion();
        }
        return;
    }

    public Joueur getCompte()
    {
        return compte;
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
        System.out.println("Rejoint "+parametres.getCarte());
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

    public void terminerPartie()
    {
        //On enlève le joueur de la partie
        if(game != null)
        {
            game.enleverJoueur(joueur);
        }
        game = null;
        //On remet le joueur en attente
        setEtat(new EtatClientAttente(this));
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

    public void envoyerListePartie()
    {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Integer> l = ControllerPacmanGameServeur.listerParties();
        ListePartie liste = new ListePartie(l);
        try
        {
            sendMessage(MessageBuilder.build("LISTER", mapper.writeValueAsString(liste)));
        } catch(IOException e)
        {
            e.printStackTrace();
        }
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
