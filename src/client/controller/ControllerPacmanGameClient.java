package client.controller;

import controller.AbstractController;
import model.AgentAction;
import model.ReaderWriter;
import model.Transfert.EtatGame;
import serveur.model.Agent;
import client.view.ViewCommand;
import client.view.ViewPacmanGame;

import java.io.IOException;
import java.net.Socket;

import javax.naming.directory.InvalidAttributesException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import client.model.PacmanGame;

public class ControllerPacmanGameClient extends AbstractController {
    ViewPacmanGame viewGame;
    ViewCommand viewCom;
    Socket socket;
    ReaderWriter rw;
    

    public ControllerPacmanGameClient(String mazePath, Socket so) throws Exception
    {
        super();
        PacmanGame g = new PacmanGame(mazePath, this);
        this.game = g;
        this.game.setMaxTurn(Integer.MAX_VALUE);
        this.viewGame = new ViewPacmanGame(this);
        this.viewCom = new ViewCommand(this);
        g.setKeyboard(viewGame.getKeyboard());
        this.game.init();
        this.socket = so;
        this.rw = new ReaderWriter(so);
    }

    /***   
        *   fonction qui retourne la vue du jeu pacman utiliser par le controleur pacman
    ***/

    public ViewPacmanGame getViewGame() {
        return viewGame;
    }

    /***   
        *   fonction qui retourne le jeu pacman utiliser par le controleur pacman
    ***/

    public PacmanGame getGame() {
        return (PacmanGame) game;
    }

    /***   
        *   fonction pour evoyer sur le serveur unr action du pacman
     * @throws InvalidAttributesException 
     * @throws JsonProcessingException 
    ***/

    public void sendAction(AgentAction action) throws JsonProcessingException, InvalidAttributesException
    {
        ObjectMapper mapper = new ObjectMapper();
        rw.getWriter().write(mapper.writeValueAsString(action));
    }

    /***   
        *   fonction pour recuperer l'etat du jeu depuis le serveur
     * @throws InvalidAttributesException 
     * @throws IOException 
    ***/

    public EtatGame getEtatGame() throws InvalidAttributesException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(rw.getReader().readLine(), EtatGame.class);
    }
}
 