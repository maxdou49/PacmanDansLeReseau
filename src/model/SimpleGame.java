/*
 * Un jeu pour tester
 */
package model;

public class SimpleGame extends Game {

    public SimpleGame()
    {
        super(); 
    }

    protected void initializeGame()
    {

    }

    protected boolean gameContinue()
    {
        return true;
    }

    protected void takeTurn()
    {
        System.out.println("Tour "+String.valueOf(turn) + " du jeu en cours");
        updateObservers();
    }

    protected void gameOver()
    {
        System.out.println("Jeu terminé");
        updateObservers();
    }
}
