/*
 * Le jeu Pacman
 */

package client.model;
import model.AgentAction;
import model.Game;
import model.KeyboadManager;
import model.Maze;
import model.Transfert.EtatGame;

import client.controller.ControllerPacmanGameClient;

public class PacmanGame extends Game {

    private Maze maze;
    protected KeyboadManager keyboard;
    StrategieKeyboard strategieKeyboard;
    private ControllerPacmanGameClient controlleur;
    private AgentAction lastAction;
    private int lives;
    private int score;
    private int level;

    public PacmanGame(ControllerPacmanGameClient controlleur) throws Exception
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

    public void run()
    {
        while(isRunning)
        {
            step();
            try
            {
                Thread.sleep(10); //On attend un peu histoire de
            } catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    protected void takeTurn()
    {
        AgentAction action = strategieKeyboard.getAction();
        if(!action.equals(lastAction)) //On ne renvoit pas si ça ne change pas afin de ne pas spammer
            controlleur.sendAction(action);
        lastAction = action;
        EtatGame etat = controlleur.getEtatGame();
        if(etat != null)
        {
            turn = etat.getTurn();
            lives = etat.getLives();
            score = etat.getScore();
            level = etat.getLevel();
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
        return String.format("Niveau: %d <br/>Score: %d \tVies: %d", level, score, lives);
    }

    @Override
    protected void initializeGame() {
        maze = controlleur.getEtatGame().getMaze();
    }
}
