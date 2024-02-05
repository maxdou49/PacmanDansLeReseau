/*
 * Le controlleur d'un jeu simple
 */
package controller;

import View.ViewCommand;
import View.ViewSimpleGame;
import model.SimpleGame;

public class ControlleurSimpleGame extends AbstractController {
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
