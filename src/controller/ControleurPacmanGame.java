/*
 * Le controlleur du jeu Pacman
 */

package controller;

import View.ViewCommand;
import View.ViewCommandPacman;
import View.ViewPacmanGame;
import model.PacmanGame;
import model.Strategie.ListeStrategie;

public class ControleurPacmanGame extends AbstractController {
    ViewPacmanGame viewGame;
    ViewCommand viewCom;

    public ControleurPacmanGame(String mazePath)
    {
        super();
        PacmanGame g = new PacmanGame(mazePath);
        game = g;
        g.setMaxTurn(Integer.MAX_VALUE);
        viewGame = new ViewPacmanGame(this);
        viewCom = new ViewCommandPacman(this);
        g.setKeyboard(viewGame.getKeyboard());
        game.init();
        
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
}
