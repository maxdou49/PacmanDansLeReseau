package client.controller;

import controller.AbstractController;
import model.AgentAction;
import model.Maze;
import model.MethodeFactory;
import model.ReaderWriter;
import model.Transfert.EtatGame;
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
    EtatGame etatGame;

    public ControllerPacmanGameClient(Socket so) throws Exception
    {
        super();

        this.socket = so;
        this.rw = new ReaderWriter(so);
        ObjectMapper mapper = new ObjectMapper();
        etatGame = mapper.readValue(rw.getReader().readLine(), EtatGame.class);
        
        PacmanGame g = new PacmanGame(this);
        this.game = g;
        this.game.setMaxTurn(Integer.MAX_VALUE);
        this.viewGame = new ViewPacmanGame(this);
        this.viewCom = new ViewCommand(this);
        g.setKeyboard(viewGame.getKeyboard());
        this.game.init();
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
        rw.getWriter().println(mapper.writeValueAsString(action));
    }

    /***   
        *   fonction pour recuperer l'etat du jeu depuis le serveur
     * @throws InvalidAttributesException 
     * @throws IOException 
    ***/

    public EtatGame getEtatGame()
    {   
        return etatGame;
    }

    /***   
        *   fonction pour recuperer la maze
    ***/

    public Maze getMaze()
    {
        return (etatGame != null) ? etatGame.getMaze() : null;
    }

    public void play()
    {
        new Thread(new Runnable() {
            ObjectMapper mapper = new ObjectMapper();
            String rd;

            @Override
            public void run() {
                try {
                    do {
                        rd = rw.getReader().readLine();
                        etatGame = mapper.readValue(rd, EtatGame.class);
                        viewGame.rafrachier(etatGame);
                        // getGame().setMaze(etatGame.getMaze());
                    } while((rd != null));
                } catch (Exception e) {
                    System.out.println(new MethodeFactory().constructMessage("ControllerClient\t"+e));
                    e.printStackTrace();
                }
            }
        }).start();
      
        game.launch();
    }
}