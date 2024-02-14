/*
 * Le jeu Pacman
 */

package client.model;
import model.Game;
import model.KeyboadManager;
import model.Maze;
import client.controller.ControllerPacmanGameClient;
public class PacmanGame extends Game {

    private Maze maze;
    protected KeyboadManager keyboard;
    private ControllerPacmanGameClient controlleur;

    public PacmanGame(String mazeFile, ControllerPacmanGameClient controlleur) throws Exception
    {
        super();
        this.controlleur = controlleur;
        this.maze = controlleur.getEtatGame().getMaze();
    }


    protected boolean gameContinue()
    {
        return true;
    }

    protected void takeTurn()
    {
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
    }

    public String getTexte()
    {
        return String.format("");
    }

    @Override
    protected void initializeGame() {
        /** Appel au controlleur pour initisaliser la game */
    }
}
