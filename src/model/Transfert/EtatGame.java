package model.Transfert;

import java.util.ArrayList;

public class EtatGame {
    protected ArrayList<EtatAgent> agents;
    protected int score;
    protected int lives;
    protected int level;
    protected int turn;
    protected EtatMaze maze;

    public EtatGame(int score, int lives, int level, int turn, EtatMaze maze)
    {
        this.score = score;
        this.lives = lives;
        this.level = level;
        this.turn = turn;
        this.agents = new ArrayList<EtatAgent>();
        this.maze = maze;
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

    public int getLevel()
    {
        return level;
    }

    public int getTurn()
    {
        return turn;
    }

    public EtatMaze getMaze()
    {
        return maze;
    }
}
