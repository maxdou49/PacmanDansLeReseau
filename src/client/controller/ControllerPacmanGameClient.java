package client.controller;

import controller.AbstractController;
import model.PacmanGame;
import model.Strategie.ListeStrategie;
import View.ViewCommand;
import View.ViewPacmanGame;

public class ControllerPacmanGameClient extends AbstractController {
    ViewPacmanGame viewGame;
    ViewCommand viewCom;

    public ControllerPacmanGameClient(String mazePath)
    {
        super();
        PacmanGame g = new PacmanGame(mazePath);
        this.game = g;
        this.game.setMaxTurn(Integer.MAX_VALUE);
        this.viewGame = new ViewPacmanGame(this);
        this.viewCom = new ViewCommand(this);
        g.setKeyboard(viewGame.getKeyboard());
        this.game.init();
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
}
 