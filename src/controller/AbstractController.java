/*
 * Le controlleur pour un jeu
 */

package controller;
import model.Game;

public abstract class AbstractController {
    protected Game game;

    public AbstractController()
    {

    }

    public void restart()
    {
        game.init();
    }

    public void step()
    {
        game.step();
    }

    public void play()
    {
        game.launch();
    }

    public void pause()
    {
        game.pause();
    }

    public void setSpeed(double speed)
    {
        game.setStepTime((long)(1000 / speed));
    }

    public Game getGame()
    {
        return game;
    }

}
