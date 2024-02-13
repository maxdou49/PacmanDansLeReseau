package client.controller;

import controller.AbstractController;
import model.ReaderWriter;
import client.model.Strategie.ListeStrategie;
import client.view.ViewCommand;
import client.view.ViewPacmanGame;

import java.io.IOException;
import java.net.Socket;

import client.model.PacmanGame;

public class ControllerPacmanGameClient extends AbstractController {
    ViewPacmanGame viewGame;
    ViewCommand viewCom;
    Socket socket;
    ReaderWriter rw;
    

    public ControllerPacmanGameClient(String mazePath, Socket so) throws IOException
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
 