/*
 * Le controlleur d'un jeu simple
 */
package controller;

import client.view.ViewCommand;
import client.view.ViewSimpleGame;
import model.SimpleGame;

public class ControlleurSimpleGame extends GameControlleur {
    ViewSimpleGame viewGame;
    ViewCommand viewCom;

    public ControlleurSimpleGame()
    {
        super();
        game = new SimpleGame();
        viewGame = new ViewSimpleGame(this);
        viewCom = new ViewCommand(this);
        game.init();
        
    }
}
