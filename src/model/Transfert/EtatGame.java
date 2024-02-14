package model.Transfert;

import java.util.ArrayList;
import model.Maze;

public class EtatGame {
    protected ArrayList<EtatAgentFantome> fantomes;
    protected ArrayList<EtatAgentPacman> pacmans;
    protected int score;
    protected int lives;
    protected int level;
    protected int turn;
    protected Maze maze;

    public void setFantomes(ArrayList<EtatAgentFantome> fantomes) {
        this.fantomes = fantomes;
    }

    public void setPacmans(ArrayList<EtatAgentPacman> pacmans) {
        this.pacmans = pacmans;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

    public EtatGame()
    {
        
    }

    public EtatGame(int score, int lives, int level, int turn, Maze maze)
    {
        this.score = score;
        this.lives = lives;
        this.level = level;
        this.turn = turn;
        this.fantomes = new ArrayList<EtatAgentFantome>();
        this.pacmans = new ArrayList<EtatAgentPacman>();
        this.maze = maze;
    }

    public void addFantome(EtatAgentFantome a)
    {
        fantomes.add(a);
    }

    public ArrayList<EtatAgentFantome> getFantomes()
    {
        return fantomes;
    }

    public void addPacman(EtatAgentPacman a)
    {
        pacmans.add(a);
    }

    public ArrayList<EtatAgentPacman> getPacmans()
    {
        return pacmans;
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

    public Maze getMaze()
    {
        return maze;
    }
}
