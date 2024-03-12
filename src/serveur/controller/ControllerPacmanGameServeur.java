package serveur.controller;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.GameControlleur;
import model.AgentAction;
import model.MethodeFactory;
import model.ReaderWriter;
import model.Transfert.EtatGame;
import model.Transfert.Message;
import model.Transfert.MessageBuilder;
import serveur.model.Agent;
import serveur.model.ClientCommunication;
import serveur.model.PacmanGame;
import serveur.model.Strategie.ListeStrategie;

public class ControllerPacmanGameServeur extends GameControlleur {

    protected ArrayList<AgentAction> clientsAction;
    protected ArrayList<ClientCommunication> clients;

    public ControllerPacmanGameServeur(String mazePath)
    {
        super();
        PacmanGame g = new PacmanGame(mazePath, this);
        this.game = g;
        this.game.setMaxTurn(Integer.MAX_VALUE);
        this.clients = new ArrayList<ClientCommunication>();
        this.clientsAction = new ArrayList<AgentAction>();
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

    public void ajouterJoueur(Socket s)
    {
        try
        {
            ClientCommunication client = new ClientCommunication(new ReaderWriter(s));
            this.clients.add(client);
            this.clientsAction.add(new AgentAction(AgentAction.STOP));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
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
            for(ClientCommunication client: clients)
            {
                client.sendMessage(msg);
            }
        } catch (Exception e)
        {
            System.out.println(new MethodeFactory().constructMessage("ControllerPacmanGameServeur\t"+e));
            e.printStackTrace();
        }
    }
}
 