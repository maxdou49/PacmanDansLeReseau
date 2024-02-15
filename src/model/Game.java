/*
 * Un jeu
 */
package model;
import java.util.ArrayList;

public abstract class Game implements Runnable, Observable {
    protected boolean isRunning;
    protected int turn;
    protected int maxTurn;
    private Thread thread;
    protected long stepTime;
    private ArrayList<Observer> observers;

    public Game()
    {
        observers = new ArrayList<Observer>();
        maxTurn = 50;
        isRunning = false;
        stepTime = 500;
    }

    public void init()
    {
        turn = 0;
        initializeGame();
    }

    abstract protected void initializeGame();

    protected boolean gameEnd()
    {
        return false;
    }
    
    public void step()
    {
        if(gameContinue() && turn < maxTurn && !gameEnd())
        {
            turn++;
            takeTurn();
        }
        else
        {
            isRunning = false;
            gameOver();
        }
    }

    abstract protected boolean gameContinue();

    abstract protected void gameOver();

    abstract protected void takeTurn();

    public void pause()
    {
        isRunning = false;
    }

    public void run()
    {
        long lastStep = System.currentTimeMillis();
        while(isRunning)
        {
            step();
            try{
                lastStep += stepTime;
                Thread.sleep(lastStep - System.currentTimeMillis());
            } catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public void launch()
    {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void setStepTime(long millis)
    {
        stepTime = millis;
    }

    public int getTurn()
    {
        return turn;
    }

    public void setMaxTurn(int maxTurn)
    {
        this.maxTurn = maxTurn;
    }

    public void addObserver(Observer o)
    {
        observers.add(o);
    }

    public void removerObserver(Observer o)
    {
        observers.remove(o);
    }

    protected void updateObservers()
    {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    public boolean isGameRunning()
    {
        return isRunning;
    }

    public String getTexte()
    {
        return "Lorem Ipsum";
    }
}