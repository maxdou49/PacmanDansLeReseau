package serveur.controller;

import java.util.Vector;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.GameControlleur;
import model.AgentAction;
import model.MethodeFactory;
import model.Transfert.EtatGame;
import model.Transfert.Message;
import model.Transfert.MessageBuilder;
import model.Transfert.MessageLancer;
import serveur.model.PacmanGame;
import serveur.model.Strategie.ListeStrategie;

public class ControllerPacmanGameServeur extends GameControlleur {

    static protected Vector<ControllerPacmanGameServeur> parties = new Vector<ControllerPacmanGameServeur>();

    protected Vector<AgentAction> clientsAction;
    protected Vector<ControlleurClient> clients;

    public ControllerPacmanGameServeur(String mazePath)
    {
        super();
        PacmanGame g = new PacmanGame(mazePath, this);
        this.game = g;
        this.game.setMaxTurn(Integer.MAX_VALUE);
        this.clients = new Vector<ControlleurClient>();
        this.clientsAction = new Vector<AgentAction>();
    }

    public void lancer()
    {
        this.game.init();
        this.game.launch();
        envoyerEtat(getGame().getEtat());
    }

    public void setStrategiePacman(ListeStrategie strategie)
    {
        PacmanGame g = (PacmanGame)game;
        g.setPacmanStrategie(strategie);
    }

    public void setStrategieFantome(ListeStrategie strategie)
    {
        PacmanGame g = (PacmanGame)game;
        g.setFantomeStrategie(strategie);
    }

    public void changeMaze(String mazeFile)
    {
        PacmanGame g = (PacmanGame)game;
        g.changeMaze(mazeFile);
    }

    public String getMaze()
    {
        PacmanGame g = (PacmanGame)game;
        return g.getMazeFile();
    }

    public void setStrategiePacmanParam(int param)
    {
        PacmanGame g = (PacmanGame)game;
        g.setPacmanStrategieParam(param);
    }

    public void setStrategieFantomeParam(int param)
    {
        PacmanGame g = (PacmanGame)game;
        g.setFantomeStrategieParam(param);
    }

    /***   
        *   fonction qui retourne le jeu pacman utiliser par le controleur pacman
    ***/

    public PacmanGame getGame() {
        return (PacmanGame) game;
    }

    public int ajouterJoueur(ControlleurClient client)
    {
        int id = 0;
        synchronized(this.clients)
        {
            id = clients.size();
            this.clients.add(client);
            this.clientsAction.add(new AgentAction(AgentAction.STOP));
        }
        return id;
    }

    public void enleverJoueur(int client)
    {
        try
        {
            synchronized(this.clients)
            {
                this.clients.set(client, null);
                this.clientsAction.set(client, new AgentAction(AgentAction.STOP));
            }
            //On supprime la partie s'il n'y a plus personne
            if(getNombreJoueurs() <= 0)
            {
                parties.remove(this);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public int getNombreJoueurs()
    {
        synchronized(this.clients)
        {
            int nb = 0;
            for(ControlleurClient c: clients)
            {
                if(c != null)
                {
                    nb++;
                }
            }
            return nb;
        }
    }

    public Vector<ControlleurClient> getJoueurs()
    {
        return clients;
    }

    public boolean assezRempli()
    {
        return getNombreJoueurs() >= getGame().getNbPlayers();
    }

    public AgentAction lireActionClient(int client)
    {
        try
        {
            return clientsAction.get(client);
        } catch(Exception e)
        {
            return new AgentAction(AgentAction.STOP);
        }
    }

    public void setActionClient(int client, AgentAction action)
    {
        try
        {
            clientsAction.set(client, action);
        } catch(Exception e)
        {

        }
    }

    public void envoyerEtat(EtatGame etat)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            Message msg = MessageBuilder.build(Message.ETAT, mapper.writeValueAsString(etat));
            for(ControlleurClient client: clients)
            {
                //System.out.println("Envoi " + client);
                if(client != null)
                {
                    client.sendMessage(msg);
                }
            }
        } catch (Exception e)
        {
            System.out.println(new MethodeFactory().constructMessage("ControllerPacmanGameServeur\t"+e));
            e.printStackTrace();
        }
    }

    public void terminerPartie()
    {
        try
        {
            Message msg = MessageBuilder.build("FIN", "");
            for(ControlleurClient client: clients)
            {
                //System.out.println("Envoi " + client);
                if(client != null)
                {
                    client.sendMessage(msg);
                }
            }
        } catch (Exception e)
        {
            System.out.println(new MethodeFactory().constructMessage("ControllerPacmanGameServeur\t"+e));
            e.printStackTrace();
        }
    }

    static public ControllerPacmanGameServeur chercherPartie(MessageLancer config)
    {
        //On cherche s'il y a une partie avec des joueurs manquant
        String carte = "layout/" + config.getCarte() + ".lay";
        for(ControllerPacmanGameServeur game: parties)
        {
            //Même carte?
            if(game.getMaze().equals(carte))
            {
                //Il y a de la place?
                if(!game.assezRempli())
                {
                    return game;
                }
            }
        }
        //Sinon on crée une nouvelle partie
        System.out.println("Création partie sur "+carte);
        ControllerPacmanGameServeur game = new ControllerPacmanGameServeur(carte);
        game.setStrategieFantome(ListeStrategie.RANDOM);
        game.setStrategiePacman(ListeStrategie.KEYBOARD);
        parties.add(game);
        return game;
    }
}
 