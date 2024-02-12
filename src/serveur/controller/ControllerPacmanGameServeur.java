package serveur.controller;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import controller.AbstractController;
import model.AgentAction;
import model.ReaderWriter;
import model.Transfert.EtatGame;
import serveur.model.ClientCommunication;
import serveur.model.PacmanGame;
import serveur.model.Strategie.ListeStrategie;

public class ControllerPacmanGameServeur extends AbstractController {

    protected ArrayList<ClientCommunication> clients;

    public ControllerPacmanGameServeur(String mazePath)
    {
        super();
        PacmanGame g = new PacmanGame(mazePath, this);
        this.game = g;
        this.game.setMaxTurn(Integer.MAX_VALUE);
        this.clients = new ArrayList<ClientCommunication>();
    }

    public void lancer()
    {
        this.game.init();
        this.game.launch();
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

    /***   
        *   fonction qui retourne le jeu pacman utiliser par le controleur pacman
    ***/

    public void ajouterJoueur(Socket s)
    {
        try
        {
            ClientCommunication client = new ClientCommunication(new ReaderWriter(s));
            this.clients.add(client);
            client.launch();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public AgentAction lireActionClient(int client)
    {
        AgentAction action = clients.get(client).getAction();
        return action;
    }

    public void envoyerEtat(EtatGame etat)
    {
        for(ClientCommunication client: clients)
        {
            client.sendState(etat);
        }
    }
}
 