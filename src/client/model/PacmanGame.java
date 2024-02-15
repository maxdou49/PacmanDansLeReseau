/*
 * Le jeu Pacman
 */

package client.model;
import model.AgentAction;
import model.Game;
import model.KeyboadManager;
import model.Maze;
import model.Transfert.EtatGame;
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

        EtatGame etat = controlleur.getEtatGame();
        if(etat != null)
        {
            this.maze = etat.getMaze();
            this.setStepTime(etat.getTimer());
        } else throw new Exception("Maze not found");
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
        } catch (JsonProcessingException | InvalidAttributesException e) {
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
        maze = controlleur.getEtatGame().getMaze();
    }
}
