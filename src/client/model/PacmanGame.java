/*
 * Le jeu Pacman
 */

package client.model;
import model.AgentAction;
import model.Game;
import model.KeyboadManager;
import model.Maze;
import model.Transfert.EtatGame;
import serveur.model.Agent;

import java.io.IOException;
import javax.naming.directory.InvalidAttributesException;

import com.fasterxml.jackson.core.JsonProcessingException;

import client.controller.ControllerPacmanGameClient;
public class PacmanGame extends Game {

    private Maze maze;
    protected KeyboadManager keyboard;
    StrategieKeyboard strategieKeyboard;
    private ControllerPacmanGameClient controlleur;

    public PacmanGame(String mazeFile, ControllerPacmanGameClient controlleur) throws Exception
    {
        super();
        this.controlleur = controlleur;
        if(controlleur.getEtatGame() != null)
            this.maze = controlleur.getEtatGame().getMaze(); 
        else throw new Exception("Maze not found");
    }


    protected boolean gameContinue()
    {
        return true;
    }

    protected void takeTurn()
    {
        try {
            AgentAction action = strategieKeyboard.getAction();
            controlleur.sendAction(action);
            System.out.println(action);
            EtatGame etat = controlleur.getEtatGame();
            maze = etat.getMaze();
            controlleur.getViewGame().rafrachier(etat);
        } catch (JsonProcessingException | InvalidAttributesException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateObservers();
    }

    protected void gameOver()
    {
        updateObservers();
    }

    public Maze getMaze()
    {
        return maze;
    }

    public void setMaze(Maze maze)
    {
        this.maze = maze;
        updateObservers();
    }

    public KeyboadManager getKeyboard()
    {
        return keyboard;
    }

    public void setKeyboard(KeyboadManager keyboard)
    {
        this.keyboard = keyboard;
        this.strategieKeyboard = new StrategieKeyboard(keyboard);
    }

    public String getTexte()
    {
        return String.format("");
    }

    @Override
    protected void initializeGame() {
        try {
            maze = controlleur.getEtatGame().getMaze();
        } catch (InvalidAttributesException | IOException e) {
            e.printStackTrace();
        }
    }
}
