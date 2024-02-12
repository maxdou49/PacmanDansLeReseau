package model.Transfert;

import java.util.ArrayList;

public class EtatGame {
    protected ArrayList<EtatAgent> agents;
    protected int score;
    protected int lives;
    protected int turn;

    public EtatGame(int score, int lives, int turn)
    {
        this.score = score;
        this.lives = lives;
        this.turn = turn;
        this.agents = new ArrayList<EtatAgent>();
    }

    public void addAgent(EtatAgent a)
    {
        agents.add(a);
    }

    public ArrayList<EtatAgent> getAgents()
    {
        return agents;
    }

    public int getScore()
    {
        return score;
    }

    public int getLives()
    {
        return lives;
    }

    public int getTurn()
    {
        return turn;
    }

    
}
